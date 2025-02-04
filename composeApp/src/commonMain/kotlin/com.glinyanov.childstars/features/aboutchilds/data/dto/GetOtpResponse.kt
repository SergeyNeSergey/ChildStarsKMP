package com.glinyanov.childstars.features.aboutchilds.data.dto

import com.glinyanov.childstars.core.data.remote.dto.DetailedStatusResponse

import kotlinx.serialization.Serializable

@Serializable
internal data class GetOtpResponse(
    val otp: String? = null,
    override val detailedStatus: EmptyResponse? = null,
    override val detailedStatusDescription: String?,
) : DetailedStatusResponse<EmptyResponse>