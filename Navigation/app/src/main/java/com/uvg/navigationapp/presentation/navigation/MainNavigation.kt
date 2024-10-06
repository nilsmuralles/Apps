package com.uvg.navigationapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.navigationapp.presentation.appFlow.mainNavigationGraph
import com.uvg.navigationapp.presentation.appFlow.navigateToMainGraph
import com.uvg.navigationapp.presentation.login.LoginDestination
import com.uvg.navigationapp.presentation.login.loginScreen

@Composable
fun MainNavigation(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination
    ) {
        loginScreen (
            onLoginClick = {
                navController.navigateToMainGraph()
            }
        )
        mainNavigationGraph()
    }
}