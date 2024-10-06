package com.uvg.navigationapp.presentation.appFlow

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uvg.navigationapp.presentation.appFlow.character.CharacterNavGraph
import com.uvg.navigationapp.presentation.appFlow.character.characterGraph
import com.uvg.navigationapp.presentation.appFlow.location.locationGraph
import com.uvg.navigationapp.presentation.appFlow.profile.profileScreen
import com.uvg.navigationapp.presentation.login.loginScreen
import com.uvg.navigationapp.presentation.navigation.CustomBottomBar
import com.uvg.navigationapp.presentation.navigation.topLevelDestinations

@SuppressLint("RestrictedApi")
@Composable
fun AppFlowScreen(
    navController: NavHostController = rememberNavController(),
    onLogOutClick: () -> Unit
) {

    var bottomBarVisible by rememberSaveable { mutableStateOf(false) }
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    bottomBarVisible = if (currentDestination != null) {
        topLevelDestinations.any { destination ->
            currentDestination.hasRoute(destination)
        }
    } else {
        false
    }

    Scaffold (
        bottomBar = {
            CustomBottomBar(
                isSelected = { destination ->
                    currentDestination?.hierarchy?.any { it.hasRoute(destination::class) } ?: false
                },
                onNavItemClick = { destination ->
                    navController.navigate(destination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CharacterNavGraph,
            modifier = Modifier.padding(innerPadding)
        ) {
            loginScreen {}
            characterGraph(navController)
            locationGraph(navController)
            profileScreen(
                modifier = Modifier.fillMaxSize(),
                onLogOutClick = onLogOutClick
            )
        }
    }
}