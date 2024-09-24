package com.uvg.navigationapp.presentation.location.locationList

import com.uvg.navigationapp.data.model.Location
import com.uvg.navigationapp.data.source.LocationDb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.navigationapp.presentation.character.characterList.CustomTopBar
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

@Composable
private fun LocationListScreen(
    locations: List<Location>,
    onLocationClick: (Int) -> Unit
){
    Column {
        CustomTopBar(
            title = "Locations",
            onBack = {  },
            hasBack = false
        )
        LazyColumn {
            items(locations) { location ->
                LocationElement(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLocationClick(location.id) }
                        .padding(25.dp),
                    location = location
                )
            }
        }
    }
}

@Composable
private fun LocationElement(
    modifier: Modifier,
    location: Location
){
    Column (
        modifier = modifier
    ){
        Row {
            Text(
                text = location.name,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Row {
            Text(
                text = location.type,
                style = MaterialTheme.typography.titleMedium
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
            LocationListScreen(
                locations = locationDb.getAllLocations()
            ) { }
        }
    }
}