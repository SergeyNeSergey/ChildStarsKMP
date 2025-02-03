package com.glinyanov.childstars.core.data.remote.dto

interface DetailedStatusResponse<TStatus> {
    val detailedStatus: TStatus?
    val detailedStatusDescription: String?
}
