package com.uvg.navigationapp.presentation.appFlow

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object AppFlowNavigationGraph

fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    this.navigate(AppFlowNavigationGraph, navOptions)
}

fun NavGraphBuilder.mainNavigationGraph(
    onLogOutClick: () -> Unit
) {
    composable<AppFlowNavigationGraph> {
        AppFlowScreen(
            onLogOutClick = onLogOutClick
        )
    }
}