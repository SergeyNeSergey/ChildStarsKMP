package com.glinyanov.childstars.features.aboutchilds.di

import com.glinyanov.childstars.features.aboutchilds.data.AboutChildsUseCaseImpl
import com.glinyanov.childstars.features.aboutchilds.data.datasource.RemoteDataSource
import com.glinyanov.childstars.features.aboutchilds.data.datasource.RemoteDataSourceImpl
import com.glinyanov.childstars.features.aboutchilds.data.mappers.Mapper
import com.glinyanov.childstars.features.aboutchilds.data.repository.AboutChildsRepository
import com.glinyanov.childstars.features.aboutchilds.data.repository.AboutChildsRepositoryImpl
import com.glinyanov.childstars.features.aboutchilds.domain.AboutChildsUseCase
import com.glinyanov.childstars.features.aboutchilds.presentation.ChildListViewModel
import com.glinyanov.childstars.features.aboutchilds.presentation.ChildListViewModelImpl
import com.glinyanov.childstars.features.aboutchilds.presentation.VoConverter
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val aboutChildsModule = module {
    factoryOf(::RemoteDataSourceImpl).bind<RemoteDataSource>()
    factoryOf(::AboutChildsRepositoryImpl).bind<AboutChildsRepository>()
    factoryOf(::Mapper)
    factoryOf(::AboutChildsUseCaseImpl).bind<AboutChildsUseCase>()
    factoryOf(::VoConverter)
    viewModelOf(::ChildListViewModelImpl).bind<ChildListViewModel>()
}