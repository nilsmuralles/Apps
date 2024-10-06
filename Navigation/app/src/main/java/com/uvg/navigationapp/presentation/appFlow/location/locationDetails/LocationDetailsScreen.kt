package com.uvg.navigationapp.presentation.appFlow.location.locationDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.navigationapp.data.model.Location
import com.uvg.navigationapp.presentation.appFlow.character.characterList.CustomTopBar
import com.uvg.navigationapp.presentation.appFlow.profile.ProfileElement
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

@Composable
fun LocationDetailsRoute(
    viewModel: LocationDetailsViewModel = viewModel(),
    onBack: () -> Unit
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LocationDetailsScreen(
        state = state,
        onBack = onBack,
        onClickWhileLoading = {
            viewModel.throwError()
        },
        onRetryClick = {
            viewModel.getLocationData()
        }
    )
}

@Composable
private fun LocationDetailsScreen(
    state: LocationDetailsState,
    onClickWhileLoading: () -> Unit,
    onRetryClick: () -> Unit,
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
        LocationDetailsContent(
            location = state.data,
            isLoading = state.isLoading,
            hasError = state.hasError,
            onClickWhileLoading = onClickWhileLoading,
            onRetryClick = onRetryClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        )
    }
}

@Composable
private fun LocationDetailsContent(
    location: Location?,
    isLoading: Boolean,
    hasError: Boolean,
    onClickWhileLoading: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box (
        modifier = modifier
    ){
        when {
            hasError -> {
                ErrorLayout(
                    label = "Error al obtener la ubicación. Intenta de nuevo",
                    onRetryClick = onRetryClick
                )
            }
            isLoading -> {
                LoadingLayout(
                    label = "Cargando ubicación",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onClickWhileLoading() }
                )
            }
            else -> {
                if (location != null) {
                    Column {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
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
            }
        }
    }
}

@Composable
fun LoadingLayout(
    modifier: Modifier,
    label: String
){
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = label
            )
        }
    }
}

@Composable
fun ErrorLayout(
    label: String,
    onRetryClick: () -> Unit
) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.error
            )
            Text(
                color = MaterialTheme.colorScheme.error,
                text = label
            )
            OutlinedButton (
                onClick = onRetryClick,
                colors =  ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = "Reintentar"
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLocationDetailsScreen() {
    NavigationAppTheme  {
        Surface {
            LocationDetailsScreen (
                state = LocationDetailsState(
                    isLoading = false,
                    data = Location(1, "Earth (C-137)", "Planet", "Dimension C-137")
                ),
                onBack = { },
                onClickWhileLoading = { },
                onRetryClick = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLocationDetailsLoadingScreen() {
    NavigationAppTheme  {
        Surface {
            LocationDetailsScreen (
                state = LocationDetailsState(
                    isLoading = true,
                    data = Location(1, "Earth (C-137)", "Planet", "Dimension C-137")
                ),
                onBack = { },
                onClickWhileLoading = { },
                onRetryClick = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLocationDetailsErrorScreen() {
    NavigationAppTheme  {
        Surface {
            LocationDetailsScreen (
                state = LocationDetailsState(
                    hasError = true,
                    data = Location(1, "Earth (C-137)", "Planet", "Dimension C-137")
                ),
                onBack = { },
                onClickWhileLoading = { },
                onRetryClick = { }
            )
        }
    }
}