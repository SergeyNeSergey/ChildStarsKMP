package com.glinyanov.childstars.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.glinyanov.childstars.book.data.database.DatabaseFactory
import com.glinyanov.childstars.book.data.database.ChildStarsDatabase
import com.glinyanov.childstars.book.data.network.KtorRemoteBookDataSource
import com.glinyanov.childstars.book.data.network.RemoteBookDataSource
import com.glinyanov.childstars.book.data.repository.DefaultBookRepository
import com.glinyanov.childstars.book.domain.BookRepository
import com.glinyanov.childstars.book.presentation.SelectedBookViewModel
import com.glinyanov.childstars.book.presentation.book_detail.BookDetailViewModel
import com.glinyanov.childstars.book.presentation.book_list.BookListViewModel
import com.glinyanov.childstars.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<ChildStarsDatabase>().favoriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}