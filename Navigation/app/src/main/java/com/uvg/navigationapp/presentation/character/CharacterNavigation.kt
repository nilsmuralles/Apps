package com.uvg.navigationapp.presentation.character

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.uvg.navigationapp.presentation.character.characterList.CharacterListDestination
import com.uvg.navigationapp.presentation.character.characterList.characterListScreen
import com.uvg.navigationapp.presentation.character.characterList.navigateToCharacterListScreen
import com.uvg.navigationapp.presentation.character.characterProfile.CharacterProfileDestination
import com.uvg.navigationapp.presentation.character.characterProfile.characterProfileScreen
import com.uvg.navigationapp.presentation.character.characterProfile.navigateToCharacterProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object CharacterNavGraph

fun NavController.navigateToCharacterGraph(navOptions: NavOptions? = null) {
    this.navigate(CharacterNavGraph, navOptions)
}

fun NavGraphBuilder.characterGraph(
    navController: NavController
) {
    navigation<CharacterNavGraph>(
        startDestination = CharacterListDestination
    ) {
        characterListScreen(
            onCharacterClick = { id ->
                navController.navigateToCharacterProfileScreen(
                    destination = CharacterProfileDestination(
                        charID = id
                    )
                )
            },
            onBack = {
                navController.navigateUp()
            }
        )
        characterProfileScreen(
            onBack = {
                navController.navigateToCharacterListScreen(
                    destination = CharacterListDestination,
                    navOptions = navOptions {
                        popUpTo(0)
                    }
                )
            }
        )
    }
}