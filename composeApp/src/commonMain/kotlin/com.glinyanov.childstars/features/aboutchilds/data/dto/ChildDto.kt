package com.glinyanov.childstars.features.aboutchilds.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChildDto(
    val name: String = "error",
    val email: String = "error",
    val otp: String = "error"
)