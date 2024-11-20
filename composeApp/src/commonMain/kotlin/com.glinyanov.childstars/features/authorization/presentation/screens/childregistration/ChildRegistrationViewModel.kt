package com.glinyanov.childstars.features.authorization.presentation.screens.childregistration

import com.glinyanov.childstars.core.presentation.design.AppNavbarItemVo
import com.glinyanov.childstars.features.authorization.presentation.AuthFlowViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal abstract class ChildRegistrationViewModel : AuthFlowViewModel() {
    abstract val state: StateFlow<State>

    abstract fun onCheckEnteredFieldsClick()
    abstract fun onChildNameFieldTextChanged(text: String)
    abstract fun onChildCodeFieldTextChanged(text: String)
    abstract fun onAlertDialogDismiss()
    abstract fun onAlertDialogPositiveButtonClick()
    abstract fun onAlertDialogNegativeButtonClick()

    sealed interface State {
        data class Content(val data: ChildRegistrationScreenVo) : State
        data object Empty : State
    }
}

internal object ChildRegistrationViewModelStub : ChildRegistrationViewModel() {
    override val state = MutableStateFlow(
        State.Content(
            ChildRegistrationScreenVo(
                navBarItems = listOf( AppNavbarItemVo.BackPressItem {}),
                childNameHint = "Введите имя ребёнка",
                childNameMessage = "",
                codeHint = "Введите код",
                codeMessage = "",
                isFieldsNotEmpty = false,
                loginButtonText = "Войти в приложение",
                alertDialogVo = null,
            )
        )
    )

    override fun onCheckEnteredFieldsClick() = Unit
    override fun onChildNameFieldTextChanged(text: String) = Unit
    override fun onChildCodeFieldTextChanged(text: String) = Unit
    override fun onAlertDialogDismiss() = Unit
    override fun onAlertDialogPositiveButtonClick() = Unit
    override fun onAlertDialogNegativeButtonClick() = Unit
}