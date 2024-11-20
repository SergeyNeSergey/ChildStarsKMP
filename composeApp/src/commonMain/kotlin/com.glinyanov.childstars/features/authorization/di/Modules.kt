package com.glinyanov.childstars.features.authorization.di

import androidx.navigation.NavHostController
import com.glinyanov.childstars.features.authorization.data.AuthorizationUseCaseImpl
import com.glinyanov.childstars.features.authorization.data.datasource.RemoteDataSource
import com.glinyanov.childstars.features.authorization.data.datasource.RemoteDataSourceImpl
import com.glinyanov.childstars.features.authorization.data.repository.AuthorizationRepository
import com.glinyanov.childstars.features.authorization.data.repository.AuthorizationRepositoryImpl
import com.glinyanov.childstars.features.authorization.domain.AuthorizationUseCase
import com.glinyanov.childstars.features.authorization.navigation.LocalNavigationDelegate
import com.glinyanov.childstars.features.authorization.navigation.LocalNavigationDelegateImpl
import com.glinyanov.childstars.features.authorization.presentation.AuthorizationViewModel
import com.glinyanov.childstars.features.authorization.presentation.AuthorizationViewModelImpl
import com.glinyanov.childstars.features.authorization.presentation.VoConverter
import com.glinyanov.childstars.features.authorization.presentation.screens.childregistration.ChildRegistrationViewModel
import com.glinyanov.childstars.features.authorization.presentation.screens.childregistration.ChildRegistrationViewModelImpl
import com.glinyanov.childstars.features.authorization.presentation.screens.parentregistration.ParentRegistrationViewModel
import com.glinyanov.childstars.features.authorization.presentation.screens.parentregistration.ParentRegistrationViewModelImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal var localNavController: NavHostController? = null

val authorizationModuleModule = module {
    factoryOf(::RemoteDataSourceImpl).bind<RemoteDataSource>()
    factoryOf(::AuthorizationRepositoryImpl).bind<AuthorizationRepository>()
    factoryOf(::AuthorizationUseCaseImpl).bind<AuthorizationUseCase>()
    factoryOf(::VoConverter)
    factoryOf(::LocalNavigationDelegateImpl).bind<LocalNavigationDelegate>()
    viewModelOf(::AuthorizationViewModelImpl).bind<AuthorizationViewModel>()
    viewModelOf(::ChildRegistrationViewModelImpl).bind<ChildRegistrationViewModel>()
    viewModelOf(::ParentRegistrationViewModelImpl).bind<ParentRegistrationViewModel>()
}