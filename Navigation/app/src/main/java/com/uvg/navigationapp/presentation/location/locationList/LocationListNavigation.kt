package com.uvg.navigationapp.presentation.location.locationList

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LocationListDestination

fun NavController.navigateToLocationList(
    destination: LocationListDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locationListScreen(
    onLocationClick: (Int) -> Unit
){
    composable<LocationListDestination>{
        LocationListRoute(
            onLocationClick = onLocationClick
        )
    }
}