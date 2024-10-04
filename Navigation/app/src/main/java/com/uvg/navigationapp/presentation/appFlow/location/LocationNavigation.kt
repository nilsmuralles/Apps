package com.uvg.navigationapp.presentation.appFlow.location

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.uvg.navigationapp.presentation.appFlow.location.locationDetails.LocationDetailsDestination
import com.uvg.navigationapp.presentation.appFlow.location.locationDetails.locationDetailsScreen
import com.uvg.navigationapp.presentation.appFlow.location.locationDetails.navigateToLocationDetailsScreen
import com.uvg.navigationapp.presentation.appFlow.location.locationList.LocationListDestination
import com.uvg.navigationapp.presentation.appFlow.location.locationList.locationListScreen
import com.uvg.navigationapp.presentation.appFlow.location.locationList.navigateToLocationList
import kotlinx.serialization.Serializable

@Serializable
data object LocationNavGraph

fun NavController.navigateToLocationGraph(navOptions: NavOptions? = null) {
    this.navigate(LocationNavGraph, navOptions)
}

fun NavGraphBuilder.locationGraph(
    navController: NavController
) {
    navigation<LocationNavGraph>(
        startDestination = LocationListDestination
    ){
        locationListScreen (
            onLocationClick = { id ->
                navController.navigateToLocationDetailsScreen(
                    destination = LocationDetailsDestination(
                        locationID = id
                    )
                )
            }
        )
        locationDetailsScreen(
            onBack = {
                navController.navigateToLocationList(
                    destination = LocationListDestination,
                    navOptions = navOptions {
                        popUpTo(0)
                    }
                )
            }
        )
    }
}