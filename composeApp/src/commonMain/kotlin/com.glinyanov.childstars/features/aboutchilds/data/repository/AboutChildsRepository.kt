package com.glinyanov.childstars.features.aboutchilds.data.repository

import com.glinyanov.childstars.features.aboutchilds.data.datasource.RemoteDataSource
import com.glinyanov.childstars.features.aboutchilds.data.dto.ChildDto

internal interface AboutChildsRepository {
    suspend fun getChilds(userId: Int): List<ChildDto>
    suspend fun getOTPCode(userId: Int): String
}

internal class AboutChildsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): AboutChildsRepository {
    override suspend fun getChilds(userId: Int) = remoteDataSource.getChilds(userId)
    override suspend fun getOTPCode(userId: Int) = remoteDataSource.getOTPCode(userId)
}