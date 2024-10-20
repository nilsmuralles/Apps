package com.uvg.navigationapp.presentation.appFlow.location.locationList

import com.uvg.navigationapp.domain.model.Location
import com.uvg.navigationapp.data.source.LocationDb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.navigationapp.presentation.appFlow.character.characterList.CustomTopBar
import com.uvg.navigationapp.presentation.appFlow.location.locationDetails.ErrorLayout
import com.uvg.navigationapp.presentation.appFlow.location.locationDetails.LoadingLayout
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

@Composable
fun LocationListRoute(
    viewModel: LocationListViewModel = viewModel(factory = LocationListViewModel.Factory),
    onLocationClick: (Int) -> Unit,
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LocationListScreen(
        state = state,
        onLocationClick = onLocationClick,
        onClickWhileLoading = {
            viewModel.throwError()
        },
        onRetryClick = {
            viewModel.getLocations()
        }
    )
}

@Composable
private fun LocationListScreen(
    state: LocationListState,
    onLocationClick: (Int) -> Unit,
    onClickWhileLoading: () -> Unit,
    onRetryClick: () -> Unit
){
    Column {
        CustomTopBar(
            title = "Locations",
            onBack = { },
            hasBack = false
        )
        LocationListContent(
            locations = state.data,
            isLoading = state.isLoading,
            hasError = state.hasError,
            onClickWhileLoading = onClickWhileLoading,
            onRetryClick = onRetryClick,
            onLocationClick = onLocationClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun LocationListContent(
    locations: List<Location>?,
    isLoading: Boolean,
    hasError: Boolean,
    onClickWhileLoading: () -> Unit,
    onRetryClick: () -> Unit,
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Box (
        modifier = modifier
    ){
        when {
            hasError -> {
                ErrorLayout(
                    label = "Error al obtener ubicaciones. Intenta de nuevo",
                    onRetryClick = onRetryClick
                )
            }
            isLoading -> {
                LoadingLayout(
                    label = "Cargando ubicaciones",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onClickWhileLoading() }
                )
            }
            else -> {
                if (locations != null) {
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
        Surface (modifier = Modifier.fillMaxSize()){
            val locationDb = LocationDb()
            LocationListScreen(
                state = LocationListState(
                    data = locationDb.getAllLocations()
                ),
                onClickWhileLoading = { },
                onRetryClick = { },
                onLocationClick = { }
            )
        }
    }
}