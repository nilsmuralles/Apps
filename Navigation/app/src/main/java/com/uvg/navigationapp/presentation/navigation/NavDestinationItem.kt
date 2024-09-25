package com.uvg.navigationapp.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavDestinationItem(
    val title: String,
    val icon: ImageVector,
    val destination: Any
)