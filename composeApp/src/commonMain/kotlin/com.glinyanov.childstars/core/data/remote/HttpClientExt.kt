package com.glinyanov.childstars.core.data.remote

import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import kotlinx.io.IOException

suspend inline fun <reified T> responseToResult(
    execute: () -> HttpResponse
): T {
    val response = execute()
    return when(response.status.value) {
        in 200..299 -> response.body<T>()
        401 -> throw UnauthorizedDataIOException(message = null)
        408 -> throw SocketTimeoutException(message = "408")
        429 -> throw TooManyAttemptsIOException(message = null)
        else -> throw IOException()
    }
}