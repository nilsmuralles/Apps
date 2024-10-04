package com.uvg.navigationapp.presentation.appFlow.character.characterProfile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class CharacterProfileDestination(
    val charID: Int
)

fun NavController.navigateToCharacterProfileScreen(
    destination: CharacterProfileDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.characterProfileScreen(
    onBack: () -> Unit
) {
    composable<CharacterProfileDestination> { backstackEntry ->
        val destination: CharacterProfileDestination = backstackEntry.toRoute()
        CharacterProfileRoute(
            charID = destination.charID,
            onBack = onBack,
            modifier = Modifier.fillMaxSize()
        )
    }
}