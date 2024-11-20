package com.glinyanov.childstars.app.di

import androidx.navigation.NavHostController
import com.glinyanov.childstars.app.navigation.AppNavigationDelegate
import com.glinyanov.childstars.core.data.remote.HttpClientFactory
import com.glinyanov.childstars.features.aboutchilds.navigation.AboutChildsFeatureNavigationDelegate
import com.glinyanov.childstars.features.authorization.navigation.AuthorizationFeatureNavigationDelegate
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

internal var navController: NavHostController? = null

internal val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::AppNavigationDelegate).bind<AboutChildsFeatureNavigationDelegate>()
    singleOf(::AppNavigationDelegate).bind<AuthorizationFeatureNavigationDelegate>()
}