package com.glinyanov.childstars.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.glinyanov.childstars.app.navigation.Route
import com.glinyanov.childstars.core.presentation.AppColors
import com.glinyanov.childstars.features.aboutchilds.presentation.ChildListScreen
import com.glinyanov.childstars.features.aboutchilds.presentation.ChildListViewModel
import com.glinyanov.childstars.features.authorization.presentation.AuthorizationScreen
import com.glinyanov.childstars.features.authorization.presentation.AuthorizationViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    LaunchedEffect(navController) { setNavController(navController) }
    MaterialTheme { ComposeApp(navController) }
}

@Composable
private fun ComposeApp(navController: NavHostController) {
    NavHost(
        modifier = Modifier.background(AppColors.LightGray),
        navController = navController,
        startDestination = Route.RootGraph
    ) {
        navigation<Route.RootGraph>(
            startDestination = Route.AuthScreen
        ) {
            composable<Route.AuthScreen>(
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End) },
                popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)},
            ) {
                AuthorizationScreen(koinViewModel<AuthorizationViewModel>())
            }

            composable<Route.ChildList>(
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End) },
                popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)},
            ) {
                ChildListScreen(koinViewModel<ChildListViewModel>())
            }
        }
    }

}

private fun setNavController(navController: NavHostController) {
    com.glinyanov.childstars.app.di.navController = navController
}