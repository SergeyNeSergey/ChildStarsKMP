package com.glinyanov.childstars.api.request

import com.google.gson.annotations.SerializedName


data class ExposedUser(@SerializedName("id") val id: Int? = null,
                           @SerializedName("name") val name: String = "error",
                           @SerializedName("email") val email: String = "error",
                           @SerializedName("password") val password: String = "error",
                           @SerializedName("role") val role: Int = -10,
                           @SerializedName("otp") val otp: String = "error"

    )
