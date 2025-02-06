package com.glinyanov.childstars.features.authorization.data

import com.glinyanov.childstars.core.data.local.CommonPrefsRepository
import com.glinyanov.childstars.core.data.remote.TooManyAttemptsIOException
import com.glinyanov.childstars.core.domain.OtpDo
import com.glinyanov.childstars.features.authorization.data.dto.AlreadyUsedIOException
import com.glinyanov.childstars.features.authorization.data.dto.BlockedIOException
import com.glinyanov.childstars.features.authorization.data.dto.IncorrectPasswordIOException
import com.glinyanov.childstars.features.authorization.data.repository.AuthorizationRepository
import com.glinyanov.childstars.features.authorization.domain.AuthErrors
import com.glinyanov.childstars.features.authorization.domain.AuthorizationUseCase
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.io.IOException

private const val CHILD_ROLE = 1
private const val PARENT_ROLE = 0

internal class AuthorizationUseCaseImpl(
    private val repository: AuthorizationRepository,
    private val prefsRepository: CommonPrefsRepository,
) : AuthorizationUseCase {
    override suspend fun login(email: String, password: String) {
        withMappingErrors { prefsRepository.setUserId(repository.login(email, password)) }
    }

    override suspend fun parentRegistration(name: String, email: String, password: String) {
        withMappingErrors {
            prefsRepository.setUserId(
                repository.registration(
                    name = name,
                    email = email,
                    password = password,
                    PARENT_ROLE
                )
            )
        }
    }

    override suspend fun childRegistration(name: String, otpCode: OtpDo) {
        withMappingErrors {
            prefsRepository.setUserId(
                repository.registrationChild(name = name, otp = otpCode.otp, role = CHILD_ROLE)
            )
        }
    }

    private inline fun <reified T> withMappingErrors(block: () -> T): T {
        return try {
            block.invoke()
        } catch (exc: IOException) {
            throw when (exc) {
                is SocketTimeoutException -> AuthErrors.IncorrectData(exc.message)
                is BlockedIOException -> AuthErrors.Forbidden(exc.message)
                is AlreadyUsedIOException -> AuthErrors.Forbidden(exc.message)
                is TooManyAttemptsIOException -> AuthErrors.ToManyAttempts(exc.message)
                is IncorrectPasswordIOException -> AuthErrors.UnknownUser(exc.message)
                else -> AuthErrors.SomethingWrong(exc.message)
            }
        }
    }
}