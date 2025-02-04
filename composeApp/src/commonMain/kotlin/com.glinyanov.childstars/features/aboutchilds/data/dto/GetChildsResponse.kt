package com.glinyanov.childstars.features.aboutchilds.data.dto

import com.glinyanov.childstars.core.data.remote.dto.DetailedStatusResponse
import kotlinx.serialization.Serializable

@Serializable
internal data class GetChildsResponse(
    val childList: List<ChildDto> = emptyList(),
    override val detailedStatus: EmptyResponse? = null,
    override val detailedStatusDescription: String?,
) : DetailedStatusResponse<EmptyResponse>