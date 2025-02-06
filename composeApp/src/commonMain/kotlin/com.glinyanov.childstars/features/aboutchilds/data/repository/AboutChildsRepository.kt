package com.glinyanov.childstars.features.aboutchilds.data.repository

import com.glinyanov.childstars.core.data.remote.UnauthorizedDataIOException
import com.glinyanov.childstars.core.data.remote.dto.OperationStatus
import com.glinyanov.childstars.features.aboutchilds.data.datasource.RemoteDataSource
import com.glinyanov.childstars.features.aboutchilds.data.dto.ChildDto
import kotlinx.io.IOException

internal interface AboutChildsRepository {
    suspend fun getChilds(userId: Int): List<ChildDto>
    suspend fun getOTPCode(userId: Int): String
}

internal class AboutChildsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : AboutChildsRepository {
    override suspend fun getChilds(userId: Int): List<ChildDto> {
        val result = remoteDataSource.getChilds(userId)
        return when {
            result.operationStatus.value == OperationStatus.FORBIDDEN -> {
                throw UnauthorizedDataIOException(result.responseData?.detailedStatusDescription)
            }

            result.operationStatus.value == OperationStatus.SUCCESS && result.responseData != null -> {
                result.responseData.childList
            }

            else -> throw IOException(result.responseData?.detailedStatusDescription)
        }
    }

    override suspend fun getOTPCode(userId: Int): String {
        val result = remoteDataSource.getOTPCode(userId)
        return when {
            result.operationStatus.value == OperationStatus.FORBIDDEN -> {
                throw UnauthorizedDataIOException(result.responseData?.detailedStatusDescription)
            }

            result.operationStatus.value == OperationStatus.SUCCESS
                    && result.responseData?.otp != null -> {
                result.responseData.otp
            }

            else -> throw IOException(result.responseData?.detailedStatusDescription)
        }

    }
}