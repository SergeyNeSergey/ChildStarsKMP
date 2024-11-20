package com.glinyanov.childstars.book.data.network

import com.glinyanov.childstars.book.data.dto.BookWorkDto
import com.glinyanov.childstars.book.data.dto.SearchResponseDto
import com.glinyanov.childstars.core.domain.DataError
import com.glinyanov.childstars.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}