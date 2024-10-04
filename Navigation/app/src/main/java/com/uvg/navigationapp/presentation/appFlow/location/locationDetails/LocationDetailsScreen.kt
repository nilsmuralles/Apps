package com.uvg.navigationapp.presentation.appFlow.location.locationDetails

import com.uvg.navigationapp.data.model.Location
import com.uvg.navigationapp.data.source.LocationDb
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.navigationapp.presentation.appFlow.character.characterList.CustomTopBar
import com.uvg.navigationapp.presentation.appFlow.profile.ProfileElement
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

val locationDb = LocationDb()

@Composable
fun LocationDetailsRoute(
    locationID: Int,
    onBack: () -> Unit
){
    val location = locationDb.getLocationById(locationID)
    LocationDetailsScreen(
        location = location,
        onBack = onBack
    )
}

@Composable
private fun LocationDetailsScreen(
    location: Location,
    onBack: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomTopBar(
            title = "Location Details",
            hasBack = true,
            onBack = onBack
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = location.name,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProfileElement(
                label = "ID",
                value = location.id.toString()
            )
            ProfileElement(
                label = "Type",
                value = location.type.toString()
            )
            ProfileElement(
                label = "Dimensions",
                value = location.dimension.toString()
            )
        }
    }
}

@Preview
@Composable
private fun LocationListPreview(){
    NavigationAppTheme {
        val locationDb = LocationDb()
        Surface (modifier = Modifier.fillMaxSize()){
            LocationDetailsScreen(
                location = locationDb.getLocationById(1),
                onBack = {  }
            )
        }
    }
}