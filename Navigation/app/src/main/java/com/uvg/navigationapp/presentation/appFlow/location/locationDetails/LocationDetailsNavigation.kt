package com.uvg.navigationapp.presentation.appFlow.location.locationDetails

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
    locationID: Int,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = LocationDetailsDestination(locationID = locationID),
        navOptions
    )
}

fun NavGraphBuilder.locationDetailsScreen(
    onBack: () -> Unit
) {
    composable<LocationDetailsDestination> {
        LocationDetailsRoute (
            onBack = onBack
        )
    }
}