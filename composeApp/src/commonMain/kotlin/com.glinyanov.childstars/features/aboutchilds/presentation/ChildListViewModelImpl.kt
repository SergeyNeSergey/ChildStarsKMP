package com.glinyanov.childstars.features.aboutchilds.presentation

import androidx.lifecycle.viewModelScope
import com.glinyanov.childstars.core.domain.OtpDo
import com.glinyanov.childstars.features.aboutchilds.domain.AboutChildsErrors
import com.glinyanov.childstars.features.aboutchilds.domain.AboutChildsUseCase
import com.glinyanov.childstars.features.aboutchilds.domain.ChildDo
import com.glinyanov.childstars.features.aboutchilds.navigation.AboutChildsFeatureNavigationDelegate
import com.glinyanov.childstars.features.aboutchilds.presentation.items.ChildItemVo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val DROP_SUBSCRIPTION_TIME_OUT = 5000L

internal class ChildListViewModelImpl(
    private val navigator: AboutChildsFeatureNavigationDelegate,
    private val converter: VoConverter,
    private val useCase: AboutChildsUseCase,
) : ChildListViewModel() {
    private val childsListState = MutableStateFlow<List<ChildDo>>(emptyList())
    private val childsListIsOnLoadingState = MutableStateFlow(false)
    private val otpIsOnLoadingState = MutableStateFlow(false)
    private val errorsState = MutableStateFlow<AboutChildsErrors?>(null)
    private val otpState = MutableStateFlow<OtpDo?>(null)

    override val state: StateFlow<State> = combine(
        childsListState,
        childsListIsOnLoadingState,
        errorsState,
        otpState,
        otpIsOnLoadingState,
    ) { childs, isChildsListLoading, errors, otp, isOtpLoading ->
        State.Content(
            converter.toVo(
                otp = otp,
                isOtpLoading = isOtpLoading,
                childs = childs,
                isChildsLoading = isChildsListLoading,
                error = errors,
            )
        )
    }
        .onStart { updateState() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(DROP_SUBSCRIPTION_TIME_OUT),
            State.Empty
        )


    override fun onChildItemClick(vo: ChildItemVo) {
        childsListState.value
            .firstOrNull { vo.name == it.name && vo.id == it.id }
            ?.let(navigator::onChildClick)
    }

    override fun onCheckCodeClick() {
        viewModelScope.launch { updateState() }
    }

    override fun onAddChildClick() {
        viewModelScope.launch {
            otpIsOnLoadingState.emit(true)
            handleErrors { otpState.emit(useCase.addChild()) }
            otpIsOnLoadingState.emit(false)
        }
    }

    override fun onAlertDialogDismiss() {
        errorsState.update { error ->
            if (error is AboutChildsErrors.Forbidden) navigator.onNeedAuthAction()
            null
        }
    }

    override fun onAlertDialogPositiveButtonClick() {
        errorsState.update { error ->
            if (error is AboutChildsErrors.Forbidden) navigator.onNeedAuthAction()
            null
        }
    }

    override fun onAlertDialogNegativeButtonClick() {
        errorsState.update { error ->
            if (error is AboutChildsErrors.Forbidden) navigator.onNeedAuthAction()
            null
        }
    }

    private suspend fun updateState() {
        childsListIsOnLoadingState.emit(true)
        handleErrors { childsListState.emit(useCase.getChilds()) }
        childsListIsOnLoadingState.emit(false)
    }

    private suspend fun handleErrors(body: suspend () -> Unit) {
        try {
            body.invoke()
        } catch (ex: AboutChildsErrors) {
            errorsState.emit(ex)
        }
    }
}