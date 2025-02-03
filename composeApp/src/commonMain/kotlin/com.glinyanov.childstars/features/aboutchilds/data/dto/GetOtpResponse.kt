package com.glinyanov.childstars.features.aboutchilds.data.dto

import com.glinyanov.childstars.core.data.remote.dto.DetailedStatusResponse
import com.glinyanov.childstars.features.authorization.data.dto.RegistrationResult
import kotlinx.serialization.Serializable

@Serializable
data class GetOtpResponse(
    val otp: String? = null,
    override val detailedStatus: RegistrationResult?,
    override val detailedStatusDescription: String?,
) : DetailedStatusResponse<RegistrationResult>