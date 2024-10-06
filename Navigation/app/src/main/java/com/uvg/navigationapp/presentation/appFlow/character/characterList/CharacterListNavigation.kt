package com.uvg.navigationapp.presentation.appFlow.character.characterList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CharacterListDestination

fun NavController.navigateToCharacterListScreen(
    destination: CharacterListDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.characterListScreen(
    onCharacterClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    composable<CharacterListDestination> {
        CharacterListRoute(
            onCharacterClick = onCharacterClick,
            onBack = onBack
        )
    }
}