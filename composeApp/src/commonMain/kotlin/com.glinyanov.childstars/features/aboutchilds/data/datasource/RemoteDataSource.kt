package com.glinyanov.childstars.features.aboutchilds.data.datasource

import com.glinyanov.childstars.core.data.remote.HttpClientFactory
import com.glinyanov.childstars.core.data.remote.dto.ResponseWrapperDto
import com.glinyanov.childstars.core.data.remote.responseToResult
import com.glinyanov.childstars.features.aboutchilds.data.dto.GetChildsResponse
import com.glinyanov.childstars.features.aboutchilds.data.dto.GetOtpResponse
import com.glinyanov.childstars.features.aboutchilds.data.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal interface RemoteDataSource {
    suspend fun getChilds(userId: Int): ResponseWrapperDto<GetChildsResponse>
    suspend fun getOTPCode(userId: Int): ResponseWrapperDto<GetOtpResponse>
}

internal class RemoteDataSourceImpl(private val httpClient: HttpClient): RemoteDataSource {

    override suspend fun getChilds(userId: Int): ResponseWrapperDto<GetChildsResponse> {
        return responseToResult {
            httpClient.post(HttpClientFactory.BASE_URL + "/getChilds") {
                contentType(ContentType.Application.Json)
                setBody(UserDto(id = userId))
            }
        }
    }

    override suspend fun getOTPCode(userId: Int): ResponseWrapperDto<GetOtpResponse> {
        return responseToResult {
            httpClient.post(HttpClientFactory.BASE_URL + "/getOtp") {
                contentType(ContentType.Application.Json)
                setBody(UserDto(id = userId))
            }
        }
    }

}