package com.glinyanov.childstars.features.authorization.presentation

import androidx.lifecycle.ViewModel
import com.glinyanov.childstars.features.authorization.domain.AuthErrors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.jvm.JvmStatic

internal open class AuthFlowViewModel : ViewModel() {

    protected val errorsState = MutableStateFlow<AuthErrors?>(null)

    protected suspend fun handleErrors(body: suspend () -> Unit) {
        try {
            body.invoke()
        } catch (ex: AuthErrors) {
            errorsState.emit(ex)
        }
    }

    companion object {
        @JvmStatic
        protected val DROP_SUBSCRIPTION_TIME_OUT = 5000L
    }
}