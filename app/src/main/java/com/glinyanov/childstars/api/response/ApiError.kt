package com.glinyanov.childstars.api.response

import com.google.gson.annotations.SerializedName

class ApiError(@SerializedName("code") val code: Int,
               @SerializedName("message") val message: String)