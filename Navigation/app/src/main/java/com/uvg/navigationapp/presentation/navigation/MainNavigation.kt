package com.uvg.navigationapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.navigationapp.presentation.appFlow.AppFlowNavigationGraph
import com.uvg.navigationapp.presentation.appFlow.AppFlowScreen
import com.uvg.navigationapp.presentation.appFlow.navigateToMainGraph
import com.uvg.navigationapp.presentation.login.LoginDestination
import com.uvg.navigationapp.presentation.login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
data object SplashScreenDestination

@Composable
fun MainNavigation(
    modifier: Modifier,
    authViewModel: MainContentViewModel = viewModel(factory = MainContentViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val authStatus by authViewModel.authStatus.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = SplashScreenDestination
    ) {
        composable<SplashScreenDestination> {

        }
        composable<LoginDestination> {
            LoginRoute(
                onLoginClick = authViewModel::login
            )
        }
        composable<AppFlowNavigationGraph> {
            AppFlowScreen (
                onLogOutClick = authViewModel::logout
            )
        }
    }

    LaunchedEffect(authStatus) {
        when (authStatus) {
            AuthStatus.Authenticated -> {
                navController.navigateToMainGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                        inclusive = true
                    ).build()
                )
            }
            AuthStatus.NonAuthenticated -> {
                navController.navigate(LoginDestination) {
                    popUpTo(0)
                }
            }
            AuthStatus.Loading -> { }
        }
    }
}