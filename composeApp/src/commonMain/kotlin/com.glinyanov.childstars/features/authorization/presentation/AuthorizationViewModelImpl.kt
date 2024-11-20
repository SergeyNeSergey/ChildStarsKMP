package com.glinyanov.childstars.features.authorization.presentation

import androidx.lifecycle.viewModelScope
import com.glinyanov.childstars.features.authorization.domain.AuthErrors
import com.glinyanov.childstars.features.authorization.domain.AuthorizationUseCase
import com.glinyanov.childstars.features.authorization.navigation.AuthorizationFeatureNavigationDelegate
import com.glinyanov.childstars.features.authorization.navigation.LocalNavigationDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class AuthorizationViewModelImpl(
    private val navigator: AuthorizationFeatureNavigationDelegate,
    private val localNavigation: LocalNavigationDelegate,
    private val useCase: AuthorizationUseCase,
    private val converter: VoConverter,
) : AuthorizationViewModel() {

    private val emailState = MutableStateFlow("")
    private val passwordState = MutableStateFlow("")
    override val state: StateFlow<State> = combine(
        emailState,
        passwordState,
        errorsState,
    ) { email, password, errors ->
        State.Content(
            converter.toVo(
                email = email,
                password = password,
                error = errors,
            )
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(DROP_SUBSCRIPTION_TIME_OUT),
        State.Empty
    )

    override fun onCheckEnteredFieldsClick() {
        viewModelScope.launch {
            handleErrors {
                val password = passwordState.first()
                val email = emailState.first()
                if (email.isBlank()) throw AuthErrors.IncorrectEmail
                if (password.isBlank()) throw AuthErrors.IncorrectDataError
                useCase.login(email = email, password = password)
                navigator.onEnterAction()
            }
        }
    }

    override fun onParentRegistrationClick() {
        localNavigation.onRegistrationParentClick()
    }

    override fun onChildRegistrationClick() {
        localNavigation.onRegistrationChildClick()
    }

    override fun onLoginFieldTextChanged(text: String) {
        viewModelScope.launch { emailState.emit(text) }
    }

    override fun onPasswordFieldTextChanged(text: String) {
        viewModelScope.launch { passwordState.emit(text) }
    }

    override fun onAlertDialogDismiss() {
        viewModelScope.launch { errorsState.emit(null) }
    }

    override fun onAlertDialogPositiveButtonClick() {
        viewModelScope.launch { errorsState.emit(null) }
    }

    override fun onAlertDialogNegativeButtonClick() {
        viewModelScope.launch { errorsState.emit(null) }
    }
}