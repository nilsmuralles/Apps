package com.uvg.navigationapp.layouts.login

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
){
    composable<LoginDestination>{
        LoginRoute(
            modifier = Modifier,
            onLoginClick = onLoginClick
        )
    }
}