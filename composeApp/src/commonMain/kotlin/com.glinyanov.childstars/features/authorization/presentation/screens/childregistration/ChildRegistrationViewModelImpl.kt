package com.glinyanov.childstars.features.authorization.presentation.screens.childregistration

import androidx.lifecycle.viewModelScope
import com.glinyanov.childstars.core.domain.OtpDo
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

internal class ChildRegistrationViewModelImpl(
    private val navigator: AuthorizationFeatureNavigationDelegate,
    private val localNavigation: LocalNavigationDelegate,
    private val converter: VoConverter,
    private val useCase: AuthorizationUseCase,
) : ChildRegistrationViewModel() {
    private val nameTextState = MutableStateFlow("")
    private val otpCodeState = MutableStateFlow("")

    override val state: StateFlow<State> = combine(
        nameTextState,
        otpCodeState,
        errorsState,
    ) { name, otp, errors ->
        State.Content(
            converter.toVo(
                childName = name,
                otpCode = otp,
                onBackPressed = ::onBackPressed,
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
                val otpCode = otpCodeState.first()
                val name = nameTextState.first()
                when {
                    name.isBlank() -> throw AuthErrors.IncorrectEmail(null)
                    otpCode.isBlank() -> throw AuthErrors.IncorrectData(null)
                }
                useCase.childRegistration(name = name, otpCode = OtpDo(otpCode))
                navigator.onEnterAction()
            }
        }
    }

    override fun onChildNameFieldTextChanged(text: String) {
        viewModelScope.launch { nameTextState.emit(text) }
    }

    override fun onChildCodeFieldTextChanged(text: String) {
        viewModelScope.launch { otpCodeState.emit(text) }
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
}