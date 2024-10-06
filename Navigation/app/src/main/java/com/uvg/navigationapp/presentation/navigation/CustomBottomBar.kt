package com.uvg.navigationapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.uvg.navigationapp.presentation.appFlow.character.CharacterNavGraph
import com.uvg.navigationapp.presentation.appFlow.location.LocationNavGraph
import com.uvg.navigationapp.presentation.appFlow.profile.ProfileDestination

val navItems = listOf(
    NavDestinationItem(
        title = "Characters",
        icon = Icons.Filled.Face,
        destination = CharacterNavGraph
    ),
    NavDestinationItem(
        title = "Locations",
        icon = Icons.Filled.Place,
        destination = LocationNavGraph
    ),
    NavDestinationItem(
        title = "Profile",
        icon = Icons.Filled.Person,
        destination = ProfileDestination
    )
)

@Composable
fun CustomBottomBar(
    isSelected: (Any) -> Boolean,
    onNavItemClick: (Any) -> Unit
){
    NavigationBar {
        navItems.forEach { navItem ->
            val isItemSelected = isSelected(navItem.destination)
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = navItem.title
                    )
                },
                selected = isItemSelected,
                onClick = {
                    onNavItemClick(navItem.destination)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}