package com.glinyanov.childstars.core.di

import com.glinyanov.childstars.core.data.local.CommonPrefsRepository
import com.glinyanov.childstars.core.data.local.CommonPrefsRepositoryImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    factoryOf(::CommonPrefsRepositoryImpl).bind<CommonPrefsRepository>()
}