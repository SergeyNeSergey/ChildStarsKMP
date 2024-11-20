package com.glinyanov.childstars.app.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.glinyanov.childstars.core.data.local.DataStoreFactory
import com.glinyanov.childstars.core.data.local.DataStorePathProvider
import com.glinyanov.childstars.core.data.local.DesktopDataStorePathProvider
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        singleOf(::DesktopDataStorePathProvider).bind<DataStorePathProvider>()
        single<DataStore<Preferences>> { DataStoreFactory.create(get()) }
    }