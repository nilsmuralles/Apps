package com.uvg.navigationapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uvg.navigationapp.presentation.character.CharacterNavGraph
import com.uvg.navigationapp.presentation.character.characterGraph
import com.uvg.navigationapp.presentation.character.characterList.CharacterListDestination
import com.uvg.navigationapp.presentation.character.navigateToCharacterGraph
import com.uvg.navigationapp.presentation.location.LocationNavGraph
import com.uvg.navigationapp.presentation.location.locationGraph
import com.uvg.navigationapp.presentation.login.LoginDestination
import com.uvg.navigationapp.presentation.login.loginScreen
import com.uvg.navigationapp.presentation.profile.ProfileDestination
import com.uvg.navigationapp.presentation.profile.profileScreen
import kotlin.reflect.typeOf

@SuppressLint("RestrictedApi")
@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navItems = listOf(
        NavDestinationItem(
            title = "Characters",
            icon = Icons.Filled.Face,
            destination = CharacterNavGraph
        ),
        NavDestinationItem(
            title = "Locations",
            icon = Icons.Filled.Place,
            destination = LocationNavGraph
        ),
        NavDestinationItem(
            title = "Profile",
            icon = Icons.Filled.Person,
            destination = ProfileDestination
        )
    )

    var selected by rememberSaveable { mutableIntStateOf(0) }
    var showNavBar by rememberSaveable { mutableStateOf(false) }

    Scaffold (
        bottomBar = {
            if(showNavBar){
                BottomNavigation {
                    NavigationBar {
                        navItems.forEachIndexed { index, navItem ->
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        imageVector = navItem.icon,
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(
                                        text = navItem.title
                                    )
                                },
                                selected = index == selected,
                                onClick = {
                                    selected = index
                                    navController.navigate(navItem.destination)
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        }
                    }
                }
            }
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            loginScreen(
                onLoginClick = {
                    navController.navigateToCharacterGraph(
                        navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                            inclusive = true
                        ).build()
                    )
                    showNavBar = true
                }
            )
            characterGraph(navController)

            locationGraph(navController)
    
            profileScreen(
                modifier = Modifier.fillMaxSize(),
                onLogOutClick = {
                    navController.navigate(LoginDestination){
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                    showNavBar = false
                }
            )
        }
    }
}