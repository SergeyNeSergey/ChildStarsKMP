package com.glinyanov.childstars.features.authorization.presentation.screens.parentregistration

import androidx.lifecycle.viewModelScope
import com.glinyanov.childstars.features.authorization.domain.AuthErrors
import com.glinyanov.childstars.features.authorization.domain.AuthorizationUseCase
import com.glinyanov.childstars.features.authorization.navigation.AuthorizationFeatureNavigationDelegate
import com.glinyanov.childstars.features.authorization.navigation.LocalNavigationDelegate
import com.glinyanov.childstars.features.authorization.presentation.VoConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class ParentRegistrationViewModelImpl(
    private val navigator: AuthorizationFeatureNavigationDelegate,
    private val localNavigation: LocalNavigationDelegate,
    private val converter: VoConverter,
    private val useCase: AuthorizationUseCase,
) : ParentRegistrationViewModel() {

    private val nameState = MutableStateFlow("")
    private val emailState = MutableStateFlow("")
    private val passwordState = MutableStateFlow("")

    override val state: StateFlow<State> = combine(
        nameState,
        emailState,
        passwordState,
        errorsState,
    ) { name, email, password, errors ->
        State.Content(
            converter.toVo(
                parentName = name,
                email = email,
                password = password,
                error = errors,
                onBackPressed = ::onBackPressed,
            )
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(DROP_SUBSCRIPTION_TIME_OUT),
        State.Empty
    )

    override fun onParentNameFieldTextChanged(text: String) {
        viewModelScope.launch { nameState.emit(text) }
    }

    override fun onEmailTextChanged(text: String) {
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

    private fun onBackPressed() {
        localNavigation.onBackPressed()
    }

    override fun onCheckEnteredFieldsClick() {
        viewModelScope.launch {
            handleErrors {
                val password = passwordState.first()
                val email = emailState.first()
                val name = nameState.first()
                when {
                    email.isBlank() -> throw AuthErrors.IncorrectEmail
                    password.isBlank() -> throw AuthErrors.IncorrectDataError
                    name.isBlank() -> throw AuthErrors.IncorrectName
                }

                useCase.parentRegistration(email = email, password = password, name = name)
                navigator.onEnterAction()
            }
        }
    }
}