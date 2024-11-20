package com.glinyanov.childstars.features.authorization.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.glinyanov.childstars.core.presentation.design.AppAlertDialogVo
import com.glinyanov.childstars.features.authorization.di.localNavController
import com.glinyanov.childstars.features.authorization.navigation.LocaLRoute
import com.glinyanov.childstars.features.authorization.presentation.screens.childregistration.ChildRegistrationScreen
import com.glinyanov.childstars.features.authorization.presentation.screens.childregistration.ChildRegistrationViewModel
import com.glinyanov.childstars.features.authorization.presentation.screens.parentregistration.ParentRegistrationScreen
import com.glinyanov.childstars.features.authorization.presentation.screens.parentregistration.ParentRegistrationViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.koin.compose.viewmodel.koinViewModel

data class AuthorizationScreenVo(
    val drawableRes: DrawableResource,
    val loginHintMessage: String,
    val loginMessage: String,
    val passwordHintMessage: String,
    val passwordMessage: String,
    val isFieldsNotEmpty: Boolean,
    val loginButtonText: String,
    val parentRegistrationButtonText: String,
    val childRegistrationButtonText: String,
    val alertDialogVo: AppAlertDialogVo?,
)

@Composable
internal fun AuthorizationScreen(viewModel: AuthorizationViewModel) {
    val screenState by viewModel.state.collectAsStateWithLifecycle()
    if (screenState is AuthorizationViewModel.State.Content) {
        val navController = rememberNavController()
        localNavController = navController
        NavHost(
            navController = navController,
            startDestination = LocaLRoute.AuthorizationGraph
        ) {
            navigation<LocaLRoute.AuthorizationGraph>(
                startDestination = LocaLRoute.Authorization
            ) {
                composable<LocaLRoute.Authorization>(
                    exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End) },
                    popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start) }
                ) {
                    AuthorizationScreenContent(
                        state = screenState as AuthorizationViewModel.State.Content,
                        onCheckEnteredFieldsClick = viewModel::onCheckEnteredFieldsClick,
                        onParentRegistrationClick = viewModel::onParentRegistrationClick,
                        onChildRegistrationClick = viewModel::onChildRegistrationClick,
                        onLoginFieldTextChanged = viewModel::onLoginFieldTextChanged,
                        onPasswordFieldTextChanged = viewModel::onPasswordFieldTextChanged,
                        onAlertDismiss = viewModel::onAlertDialogDismiss,
                        onAlertNegativeClick = viewModel::onAlertDialogNegativeButtonClick,
                        onAlertPositiveClick = viewModel::onAlertDialogPositiveButtonClick,
                    )
                }

                composable<LocaLRoute.ChildRegistration>(
                    exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End) },
                    enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start) },
                ) {
                    ChildRegistrationScreen(koinViewModel<ChildRegistrationViewModel>())
                }

                composable<LocaLRoute.ParentRegistration>(
                    exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End) },
                    enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start) },
                ) {
                    ParentRegistrationScreen(koinViewModel<ParentRegistrationViewModel>())
                }
            }
        }
    }
}

@Composable
fun AuthorizationScreenPreview() {
    AuthorizationScreen(AuthorizationViewModelStub)
}



