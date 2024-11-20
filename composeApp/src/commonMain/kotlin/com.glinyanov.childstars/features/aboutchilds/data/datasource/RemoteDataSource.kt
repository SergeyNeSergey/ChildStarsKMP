package com.glinyanov.childstars.features.aboutchilds.data.datasource

import com.glinyanov.childstars.core.data.remote.HttpClientFactory
import com.glinyanov.childstars.core.data.remote.responseToResult
import com.glinyanov.childstars.features.aboutchilds.data.dto.ChildDto
import com.glinyanov.childstars.features.aboutchilds.data.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal interface RemoteDataSource {
    suspend fun getChilds(userId: Int): List<ChildDto>
    suspend fun getOTPCode(userId: Int): String
}

internal class RemoteDataSourceImpl(private val httpClient: HttpClient): RemoteDataSource {

    override suspend fun getChilds(userId: Int): List<ChildDto> {
        return responseToResult {
            httpClient.post(HttpClientFactory.BASE_URL + "/getChilds") {
                contentType(ContentType.Application.Json)
                setBody(UserDto(id = userId))
            }
        }
    }

    override suspend fun getOTPCode(userId: Int): String {
        return responseToResult {
            httpClient.post(HttpClientFactory.BASE_URL + "/getOtp") {
                contentType(ContentType.Application.Json)
                setBody(UserDto(id = userId))
            }
        }
    }

}