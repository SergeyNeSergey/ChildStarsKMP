package com.glinyanov.childstars.features.authorization.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val name: String = "error",
    val email: String = "error",
    val password: String = "error",
    val role: Int = -10,
    val otp: String = "error"
)