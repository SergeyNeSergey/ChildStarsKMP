package com.glinyanov.childstars.features.authorization.presentation

import childstars.composeapp.generated.resources.Res
import childstars.composeapp.generated.resources.main_logo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal abstract class AuthorizationViewModel : AuthFlowViewModel() {
    abstract val state: StateFlow<State>

    abstract fun onCheckEnteredFieldsClick()
    abstract fun onParentRegistrationClick()
    abstract fun onChildRegistrationClick()
    abstract fun onLoginFieldTextChanged(text: String)
    abstract fun onPasswordFieldTextChanged(text: String)
    abstract fun onAlertDialogDismiss()
    abstract fun onAlertDialogPositiveButtonClick()
    abstract fun onAlertDialogNegativeButtonClick()

    sealed interface State {
        data class Content(val data: AuthorizationScreenVo) : State
        data object Empty : State
    }
}

internal object AuthorizationViewModelStub : AuthorizationViewModel() {
    override val state = MutableStateFlow(
        State.Content(
            AuthorizationScreenVo(
                drawableRes = Res.drawable.main_logo,
                loginHintMessage = "Введите e-mail",
                loginMessage = "",
                passwordHintMessage = "Введите пароль",
                passwordMessage = "",
                isFieldsNotEmpty = false,
                loginButtonText = "Войти в приложение",
                parentRegistrationButtonText = "Регистрация родителя",
                childRegistrationButtonText = "Регистрация ребенка",
                alertDialogVo = null,
            )
        )
    )

    override fun onCheckEnteredFieldsClick() = Unit
    override fun onParentRegistrationClick() = Unit
    override fun onChildRegistrationClick() = Unit
    override fun onLoginFieldTextChanged(text: String) = Unit
    override fun onPasswordFieldTextChanged(text: String) = Unit
    override fun onAlertDialogDismiss() = Unit
    override fun onAlertDialogPositiveButtonClick() = Unit
    override fun onAlertDialogNegativeButtonClick() = Unit
}