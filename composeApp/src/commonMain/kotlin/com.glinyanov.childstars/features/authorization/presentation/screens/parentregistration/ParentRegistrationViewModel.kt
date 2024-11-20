package com.glinyanov.childstars.features.authorization.presentation.screens.parentregistration

import com.glinyanov.childstars.core.presentation.design.AppNavbarItemVo
import com.glinyanov.childstars.features.authorization.presentation.AuthFlowViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal abstract class ParentRegistrationViewModel : AuthFlowViewModel() {
    abstract val state: StateFlow<State>

    abstract fun onCheckEnteredFieldsClick()
    abstract fun onParentNameFieldTextChanged(text: String)
    abstract fun onEmailTextChanged(text: String)
    abstract fun onPasswordFieldTextChanged(text: String)
    abstract fun onAlertDialogDismiss()
    abstract fun onAlertDialogPositiveButtonClick()
    abstract fun onAlertDialogNegativeButtonClick()

    sealed interface State {
        data class Content(val data: ParentRegistrationScreenVo) : State
        data object Empty : State
    }
}

internal object ParentRegistrationViewModelStub : ParentRegistrationViewModel() {
    override val state = MutableStateFlow(
        State.Content(
            ParentRegistrationScreenVo(
                navBarItems = listOf( AppNavbarItemVo.BackPressItem {}),
                parentNameHint = "Введите имя",
                parentNameMessage = "",
                emailHint = "Введите e-mail",
                emailMessage = "",
                passwordHint = "Введите пароль",
                passwordMessage = "",
                isFieldsNotEmpty = false,
                loginButtonText = "Войти в приложение",
                alertDialogVo = null,
            )
        )
    )

    override fun onCheckEnteredFieldsClick() = Unit
    override fun onParentNameFieldTextChanged(text: String) = Unit
    override fun onEmailTextChanged(text: String) = Unit
    override fun onPasswordFieldTextChanged(text: String) = Unit
    override fun onAlertDialogDismiss() = Unit
    override fun onAlertDialogPositiveButtonClick() = Unit
    override fun onAlertDialogNegativeButtonClick() = Unit
}