package com.glinyanov.childstars.book.presentation.book_detail

import com.glinyanov.childstars.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
