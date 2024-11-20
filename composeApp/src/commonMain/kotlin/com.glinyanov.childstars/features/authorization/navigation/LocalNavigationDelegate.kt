package com.glinyanov.childstars.features.authorization.navigation

import com.glinyanov.childstars.features.authorization.di.localNavController

//TODO Подобные интерфейсы нужны на случай написания юнит тестов
internal interface LocalNavigationDelegate {

    fun onRegistrationChildClick()
    fun onRegistrationParentClick()
    fun onBackPressed()
}

internal class LocalNavigationDelegateImpl: LocalNavigationDelegate {
    override fun onRegistrationChildClick() {
        localNavController?.navigate(LocaLRoute.ChildRegistration)
    }

    override fun onRegistrationParentClick() {
        localNavController?.navigate(LocaLRoute.ParentRegistration)
    }

    override fun onBackPressed() {
        localNavController?.popBackStack()
    }
}