package com.glinyanov.childstars.app.di

import com.glinyanov.childstars.core.di.coreModule
import com.glinyanov.childstars.features.aboutchilds.di.aboutChildsModule
import com.glinyanov.childstars.features.authorization.di.authorizationModuleModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

internal fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            sharedModule,
            platformModule,
            aboutChildsModule,
            authorizationModuleModule,
            coreModule
        )
    }
}