package com.glinyanov.childstars.features.authorization.data.repository

import com.glinyanov.childstars.core.data.remote.TooManyAttemptsIOException
import com.glinyanov.childstars.core.data.remote.UnauthorizedDataIOException
import com.glinyanov.childstars.core.data.remote.dto.OperationStatus
import com.glinyanov.childstars.core.data.remote.dto.ResponseWrapperDto
import com.glinyanov.childstars.features.authorization.data.datasource.RemoteDataSource
import com.glinyanov.childstars.features.authorization.data.dto.AlreadyUsedIOException
import com.glinyanov.childstars.features.authorization.data.dto.BlockedIOException
import com.glinyanov.childstars.features.authorization.data.dto.IncorrectPasswordIOException
import com.glinyanov.childstars.features.authorization.data.dto.RegistrationResponse
import com.glinyanov.childstars.features.authorization.data.dto.RegistrationResult
import com.glinyanov.childstars.features.authorization.data.dto.UserDto
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.io.IOException

internal interface AuthorizationRepository {
    suspend fun registration(name: String, email: String, password: String, role: Int): Int
    suspend fun registrationChild(name: String, otp: String, role: Int): Int
    suspend fun login(email: String, password: String): Int
}

internal class AuthorizationRepositoryImpl(
    private val dataSource: RemoteDataSource
) : AuthorizationRepository {

    override suspend fun registration(
        name: String,
        email: String,
        password: String,
        role: Int
    ): Int {
        return dataSource
            .registration(
                UserDto(
                    name = name,
                    email = email,
                    password = password,
                    role = role
                )
            )
            .getRawResult()
    }

    override suspend fun registrationChild(name: String, otp: String, role: Int): Int {
        return dataSource.registration(
            UserDto(
                name = name,
                otp = otp,
                role = role
            )
        )
            .getRawResult()
    }

    override suspend fun login(email: String, password: String): Int {
        return dataSource.login(UserDto(email = email, password = password)).getRawResult()
    }

    private fun ResponseWrapperDto<RegistrationResponse>.getRawResult(): Int {
        return when {
            operationStatus.value == OperationStatus.FORBIDDEN -> {
                throw UnauthorizedDataIOException(responseData?.detailedStatusDescription)
            }

            operationStatus.value == OperationStatus.SUCCESS && responseData != null -> {
                responseData.getUserId()
            }

            else -> throw IOException(responseData?.detailedStatusDescription)
        }
    }

    private fun RegistrationResponse.getUserId(): Int {
        return when (detailedStatus?.value) {
            RegistrationResult.TIMEOUT -> throw SocketTimeoutException(message = "408")
            RegistrationResult.BLOCKED -> throw BlockedIOException(detailedStatusDescription)
            RegistrationResult.ALREADY_USED -> throw AlreadyUsedIOException(detailedStatusDescription)
            RegistrationResult.ATTEMPTS_LIMIT_EXCEEDED -> throw TooManyAttemptsIOException(detailedStatusDescription)
            RegistrationResult.INCORRECT_PASSWORD -> throw IncorrectPasswordIOException(detailedStatusDescription)
            else -> id
        }
    }
}