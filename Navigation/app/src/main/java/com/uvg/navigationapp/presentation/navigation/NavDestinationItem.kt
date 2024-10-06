package com.uvg.navigationapp.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.uvg.navigationapp.presentation.appFlow.character.characterList.CharacterListDestination
import com.uvg.navigationapp.presentation.appFlow.location.locationList.LocationListDestination
import com.uvg.navigationapp.presentation.appFlow.profile.ProfileDestination

data class NavDestinationItem(
    val title: String,
    val icon: ImageVector,
    val destination: Any
)

val topLevelDestinations = listOf(
    CharacterListDestination::class,
    LocationListDestination::class,
    ProfileDestination::class
)

