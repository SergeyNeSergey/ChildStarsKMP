package com.glinyanov.childstars.features.authorization.domain

import com.glinyanov.childstars.core.domain.OtpDo

interface AuthorizationUseCase {

    suspend fun login(email: String, password: String)
    suspend fun parentRegistration(name: String, email: String, password: String)
    suspend fun childRegistration(name: String, otpCode : OtpDo)
}