package com.glinyanov.childstars.viewmodels

import androidx.lifecycle.ViewModel
import com.glinyanov.childstars.api.Api
import com.glinyanov.childstars.api.response.RegistrationResponse
import com.glinyanov.childstars.api.response.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: Api
) : ViewModel() {

    suspend fun login(email: String, password: String): ResponseWrapper<RegistrationResponse> {
        return api.login(email, password)
    }
}
