package com.glinyanov.childstars.features.authorization.data.datasource

import com.glinyanov.childstars.core.data.remote.HttpClientFactory
import com.glinyanov.childstars.core.data.remote.dto.ResponseWrapperDto
import com.glinyanov.childstars.core.data.remote.responseToResult
import com.glinyanov.childstars.features.authorization.data.dto.RegistrationResponse
import com.glinyanov.childstars.features.authorization.data.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal interface RemoteDataSource {
    suspend fun registration(user: UserDto): ResponseWrapperDto<RegistrationResponse>
    suspend fun login(user: UserDto): ResponseWrapperDto<RegistrationResponse>
}

internal class RemoteDataSourceImpl(private val httpClient: HttpClient): RemoteDataSource {

    override suspend fun registration(user: UserDto): ResponseWrapperDto<RegistrationResponse> {
        return responseToResult {
            httpClient.post(HttpClientFactory.BASE_URL + "/login") {
                contentType(ContentType.Application.Json)
                setBody(user)
            }
        }
    }

    override suspend fun login(user: UserDto): ResponseWrapperDto<RegistrationResponse> {
        return responseToResult {
            httpClient.post(HttpClientFactory.BASE_URL + "/addUser") {
                contentType(ContentType.Application.Json)
                setBody(user)
            }
        }
    }

}