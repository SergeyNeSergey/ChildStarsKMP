package com.glinyanov.childstars.features.authorization.navigation

import kotlinx.serialization.Serializable

internal sealed interface LocaLRoute {

    @Serializable
    data object AuthorizationGraph: LocaLRoute
    @Serializable
    data object Authorization: LocaLRoute

    @Serializable
    data object ChildRegistration: LocaLRoute

    @Serializable
    data object ParentRegistration: LocaLRoute
}