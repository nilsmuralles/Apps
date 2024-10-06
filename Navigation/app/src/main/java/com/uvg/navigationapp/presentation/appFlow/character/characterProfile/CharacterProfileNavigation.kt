package com.uvg.navigationapp.presentation.appFlow.character.characterProfile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CharacterProfileDestination(
    val charID: Int
)

fun NavController.navigateToCharacterProfileScreen(
    charID: Int,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = CharacterProfileDestination(charID = charID),
        navOptions
    )
}

fun NavGraphBuilder.characterProfileScreen(
    onBack: () -> Unit
) {
    composable<CharacterProfileDestination> {
        CharacterProfileRoute(
            onBack = onBack
        )
    }
}