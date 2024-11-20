package com.glinyanov.childstars.features.authorization.data

import com.glinyanov.childstars.core.data.local.CommonPrefsRepository
import com.glinyanov.childstars.core.domain.OtpDo
import com.glinyanov.childstars.features.authorization.data.repository.AuthorizationRepository
import com.glinyanov.childstars.features.authorization.domain.AuthorizationUseCase

private const val CHILD_ROLE = 1
private const val PARENT_ROLE = 0

internal class AuthorizationUseCaseImpl(
    private val repository: AuthorizationRepository,
    private val prefsRepository: CommonPrefsRepository,
) : AuthorizationUseCase {
    override suspend fun login(email: String, password: String) {
        prefsRepository.setUserId(repository.login(email, password))
    }

    override suspend fun parentRegistration(name: String, email: String, password: String) {
        prefsRepository.setUserId(
            repository.registration(name = name, email = email, password = password, PARENT_ROLE)
        )
    }

    override suspend fun childRegistration(name: String, otpCode: OtpDo) {
        prefsRepository.setUserId(
            repository.registrationChild(name = name, otp = otpCode.otp, role = CHILD_ROLE)
        )
    }
}