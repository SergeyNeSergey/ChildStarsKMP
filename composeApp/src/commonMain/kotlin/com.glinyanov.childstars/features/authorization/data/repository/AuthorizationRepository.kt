package com.glinyanov.childstars.features.authorization.data.repository

import com.glinyanov.childstars.features.authorization.data.datasource.RemoteDataSource
import com.glinyanov.childstars.features.authorization.data.dto.UserDto

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
        return dataSource.registration(
            UserDto(
                name = name,
                email = email,
                password = password,
                role = role
            )
        )
    }

    override suspend fun registrationChild(name: String, otp: String, role: Int): Int {
        return dataSource.registration(UserDto(name = name, otp = otp, role = role))
    }

    override suspend fun login(email: String, password: String): Int {
        return dataSource.login(UserDto(email = email, password = password))
    }
}