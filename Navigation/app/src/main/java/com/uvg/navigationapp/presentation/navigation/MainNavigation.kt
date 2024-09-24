package com.uvg.navigationapp.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.uvg.navigationapp.presentation.character.characterGraph
import com.uvg.navigationapp.presentation.character.characterList.CharacterListDestination
import com.uvg.navigationapp.presentation.character.characterList.navigateToCharacterListScreen
import com.uvg.navigationapp.presentation.character.navigateToCharacterGraph
import com.uvg.navigationapp.presentation.login.LoginDestination
import com.uvg.navigationapp.presentation.login.loginScreen

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = LoginDestination,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = {
                navController.navigateToCharacterGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                        inclusive = true
                    ).build()
                )
            }
        )
        characterGraph(navController)
    }
}