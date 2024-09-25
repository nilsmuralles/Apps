package com.uvg.navigationapp.presentation.location.locationDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailsDestination(
    val locationID: Int
)

fun NavController.navigateToLocationDetailsScreen(
    destination: LocationDetailsDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locationDetailsScreen(
    onBack: () -> Unit
) {
    composable<LocationDetailsDestination> { backstackEntry ->
        val destination: LocationDetailsDestination = backstackEntry.toRoute()
        LocationDetailsRoute (
            locationID = destination.locationID,
            onBack = onBack
        )
    }
}