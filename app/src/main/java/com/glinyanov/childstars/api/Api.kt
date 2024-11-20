package com.glinyanov.childstars.api

import com.glinyanov.childstars.api.request.ExposedUser
import com.glinyanov.childstars.api.response.GetChildResponse
import com.glinyanov.childstars.api.response.OtpResponse
import com.glinyanov.childstars.api.response.RegistrationResponse
import com.glinyanov.childstars.api.response.ResponseWrapper
import javax.inject.Inject

class Api @Inject constructor(private val childStarsService: ChildStarsService) {

    suspend fun registration(name: String, email: String, password: String, role: Int): ResponseWrapper<RegistrationResponse> {
        return childStarsService.registration(ExposedUser(name = name, email = email, password = password, role = role))
    }

    suspend fun registrationChild(name: String, otp: String, role: Int): ResponseWrapper<RegistrationResponse> {
        return childStarsService.registration(ExposedUser(name = name, otp = otp, role = role))
    }

    suspend fun login(email: String, password: String): ResponseWrapper<RegistrationResponse> {
        return childStarsService.login(ExposedUser(email = email, password = password))
    }

    suspend fun getOtp(id: Int): ResponseWrapper<OtpResponse> {
        return childStarsService.getOtp(ExposedUser(id = id))
    }

    suspend fun getChilds(id: Int): ResponseWrapper<GetChildResponse> {
        return childStarsService.getChilds(ExposedUser(id = id))
    }
}