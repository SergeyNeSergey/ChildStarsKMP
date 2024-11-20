package com.glinyanov.childstars.app.navigation

import kotlinx.serialization.Serializable

internal sealed interface Route {

    @Serializable
    data object RootGraph: Route

    @Serializable
    data object ChildList: Route

    @Serializable
    data object TaskList: Route

    @Serializable
    data object AuthScreen: Route
}