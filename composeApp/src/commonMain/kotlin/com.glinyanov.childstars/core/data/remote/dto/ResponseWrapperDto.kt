package com.glinyanov.childstars.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseWrapperDto<T>(
    val operationStatus: OperationStatus = OperationStatus(OperationStatus.SUCCESS),
    val responseData: T? = null
)