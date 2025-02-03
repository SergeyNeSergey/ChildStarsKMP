package com.glinyanov.childstars.features.authorization.data.dto

import com.glinyanov.childstars.core.data.remote.dto.DetailedStatusResponse
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val id: Int = -1,
    override val detailedStatus: RegistrationResult?,
    override val detailedStatusDescription: String?,
) : DetailedStatusResponse<RegistrationResult>
