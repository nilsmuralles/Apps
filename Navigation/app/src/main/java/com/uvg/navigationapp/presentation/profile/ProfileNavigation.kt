package com.uvg.navigationapp.presentation.profile

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.uvg.navigationapp.presentation.character.characterProfile.navigateToCharacterProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavController.navigateToProfileScreen(
    destination: ProfileDestination,
    navOptions: NavOptions? = null
){
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.profileScreen(
    modifier: Modifier,
    onLogOutClick: () -> Unit
){
    composable<ProfileDestination>{
        ProfileRoute(
            modifier = modifier,
            onLogOutClick = onLogOutClick
        )
    }
}