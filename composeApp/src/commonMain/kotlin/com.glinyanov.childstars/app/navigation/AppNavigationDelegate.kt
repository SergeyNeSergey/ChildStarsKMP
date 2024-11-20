package com.glinyanov.childstars.app.navigation

import com.glinyanov.childstars.app.di.navController
import com.glinyanov.childstars.features.aboutchilds.domain.ChildDo
import com.glinyanov.childstars.features.aboutchilds.navigation.AboutChildsFeatureNavigationDelegate
import com.glinyanov.childstars.features.authorization.navigation.AuthorizationFeatureNavigationDelegate

internal class AppNavigationDelegate
    : AboutChildsFeatureNavigationDelegate, AuthorizationFeatureNavigationDelegate {
    override fun onChildClick(child: ChildDo) {
        navController?.navigateUp()
    }

    override fun onEnterAction() {
        navController?.navigate(Route.ChildList)
    }

    override fun onNeedAuthAction() {
        navController?.navigate(Route.AuthScreen)
        navController?.clearBackStack<Route.RootGraph>()
    }
}