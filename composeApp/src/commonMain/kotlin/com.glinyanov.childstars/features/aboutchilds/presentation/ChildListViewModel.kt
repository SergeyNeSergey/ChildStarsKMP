package com.glinyanov.childstars.features.aboutchilds.presentation

import androidx.lifecycle.ViewModel
import childstars.composeapp.generated.resources.Res
import childstars.composeapp.generated.resources.main_logo
import com.glinyanov.childstars.features.aboutchilds.presentation.items.ChildItemVo
import com.glinyanov.childstars.features.aboutchilds.presentation.items.ChildListItemVo
import com.glinyanov.childstars.features.aboutchilds.presentation.items.CodeItemVo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class ChildListViewModel: ViewModel() {
    abstract val state: StateFlow<State>

    abstract fun onChildItemClick(vo: ChildItemVo)
    abstract fun onCheckCodeClick()
    abstract fun onAddChildClick()
    abstract fun onAlertDialogDismiss()
    abstract fun onAlertDialogPositiveButtonClick()
    abstract fun onAlertDialogNegativeButtonClick()

    sealed interface State {
        data class Content(val data: ChildListScreenVo): State
        data object Empty: State
    }
}

object ChildListViewModelStub: ChildListViewModel() {
    override val state = MutableStateFlow(
        State.Content(
            ChildListScreenVo(
                drawableRes = Res.drawable.main_logo,
                addChildText = "Добавить ребенка",
                codeItemVo = CodeItemVo(
                    state = CodeItemVo.State.Show,
                    hintMessage = "Введите этот код при регистрации на устройстве ребенка",
                    checkButtonText = "Проверить",
                    codeText = "1234"
                ),
                alertDialogVo = null,
                childListVo = ChildListItemVo(
                    isChildListLoading = false,
                    childList = listOf(
                        ChildItemVo("Саша Остапенко",""), ChildItemVo("Лёша Павлов","")
                    )
                )
            )
        )
    )

    override fun onChildItemClick(vo: ChildItemVo) = Unit
    override fun onCheckCodeClick() = Unit
    override fun onAddChildClick() = Unit
    override fun onAlertDialogDismiss() = Unit
    override fun onAlertDialogPositiveButtonClick() = Unit
    override fun onAlertDialogNegativeButtonClick() = Unit
}