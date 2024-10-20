package com.uvg.navigationapp.presentation.appFlow.character.characterProfile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.uvg.navigationapp.domain.model.Character
import com.uvg.navigationapp.presentation.appFlow.character.characterList.CustomTopBar
import com.uvg.navigationapp.presentation.appFlow.location.locationDetails.ErrorLayout
import com.uvg.navigationapp.presentation.appFlow.location.locationDetails.LoadingLayout
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

@Composable
fun CharacterProfileRoute(
    viewModel: CharacterProfileViewModel = viewModel(),
    onBack: () -> Unit
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    CharacterProfileScreen(
        state = state,
        onBack = onBack,
        onClickWhileLoading = {
            viewModel.throwError()
        },
        onRetryClick = {
            viewModel.getCharacterData()
        }
    )
}

@Composable
private fun CharacterProfileScreen(
    state: CharacterProfileState,
    onClickWhileLoading: () -> Unit,
    onRetryClick: () -> Unit,
    onBack: () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        CustomTopBar(
            title = "Character Detail",
            onBack = onBack,
            hasBack = true
        )
        CharacterProfileContent(
            character = state.data,
            isLoading = state.isLoading,
            hasError = state.hasError,
            onClickWhileLoading = onClickWhileLoading,
            onRetryClick = onRetryClick,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun CharacterProfileContent(
    character: Character?,
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
                    label = "Error al obtener el personaje. Intenta de nuevo",
                    onRetryClick = onRetryClick
                )
            }
            isLoading -> {
                LoadingLayout(
                    label = "Cargando personaje",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onClickWhileLoading() }
                )
            }
            else -> {
                if (character != null) {
                    Column {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                            ,
                            horizontalArrangement = Arrangement.Center
                        ){
                            AsyncImage(
                                model = character.image,
                                contentDescription = character.name,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(200.dp)
                            )
                        }
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = character.name,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                        Row (
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 60.dp, vertical = 30.dp)
                        ){
                            Column {
                                Text(text = "Species:")
                                Text(text = "Status:")
                                Text(text = "Gender:")
                            }
                            Column {
                                Text(text = character.species)
                                Text(text = character.status)
                                Text(text = character.gender)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CharacterProfileScreenPreview(){
    NavigationAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CharacterProfileScreen(
                state = CharacterProfileState(
                    isLoading = false,
                    data = Character(1, "Rick Sanchez", "Alive", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
                    hasError = false
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
private fun CharacterProfileLoadingScreenPreview(){
    NavigationAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CharacterProfileScreen(
                state = CharacterProfileState(
                    isLoading = true,
                    data = Character(1, "Rick Sanchez", "Alive", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
                    hasError = false
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
private fun CharacterProfileErrorScreenPreview(){
    NavigationAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CharacterProfileScreen(
                state = CharacterProfileState(
                    isLoading = false,
                    data = Character(1, "Rick Sanchez", "Alive", "Human", "Male", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
                    hasError = true
                ),
                onBack = { },
                onClickWhileLoading = { },
                onRetryClick = { }
            )
        }
    }
}