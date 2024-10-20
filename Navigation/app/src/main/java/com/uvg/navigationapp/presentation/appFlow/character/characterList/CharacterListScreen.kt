package com.uvg.navigationapp.presentation.appFlow.character.characterList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.uvg.navigationapp.data.source.CharacterDb
import com.uvg.navigationapp.domain.model.Character
import com.uvg.navigationapp.presentation.appFlow.location.locationDetails.ErrorLayout
import com.uvg.navigationapp.presentation.appFlow.location.locationDetails.LoadingLayout
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

@Composable
fun CharacterListRoute(
    viewModel: CharacterLIstViewModel = viewModel(factory = CharacterLIstViewModel.Factory),
    onCharacterClick: (Int) -> Unit,
    onBack: () -> Unit
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    CharacterListScreen(
        state = state,
        onCharacterClick = onCharacterClick,
        onClickWhileLoading = {
            viewModel.throwError()
        },
        onRetryClick = {
            viewModel.getCharacters()
        },
        onBack = onBack
    )
}

@Composable
fun CharacterListScreen(
    state: CharacterLIstState,
    onCharacterClick: (Int) -> Unit,
    onClickWhileLoading: () -> Unit,
    onRetryClick: () -> Unit,
    onBack: () -> Unit
){
    Column {
        CustomTopBar(
            title = "Characters",
            onBack = onBack,
            hasBack = false
        )
        CharacterListContent(
            characters = state.data,
            isLoading = state.isLoading,
            hasError = state.hasError,
            onClickWhileLoading = onClickWhileLoading,
            onRetryClick = onRetryClick,
            onCharacterClick = onCharacterClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CharacterListContent(
    characters: List<Character>?,
    isLoading: Boolean,
    hasError: Boolean,
    onClickWhileLoading: () -> Unit,
    onRetryClick: () -> Unit,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier
){
    Box (
        modifier = modifier
    ) {
        when {
            hasError -> {
                ErrorLayout(
                    label = "Error al obtener personajes. Intenta de nuevo",
                    onRetryClick = onRetryClick
                )
            }
            isLoading -> {
                LoadingLayout(
                    label = "Cargando personajes",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onClickWhileLoading() }
                )
            }
            else -> {
                if (characters != null) {
                    LazyColumn {
                        items(characters) { character ->
                            Character(
                                image = character.image,
                                name = character.name,
                                species = character.species,
                                status = character.status,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(25.dp)
                                    .clickable { onCharacterClick(character.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    hasBack: Boolean,
    onBack: () -> Unit
){
    if (hasBack){
        TopAppBar(
            title = {
                Text(title)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = {
                Text(title)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            )
        )
    }
}

@Composable
private fun Character(
    modifier: Modifier,
    image: String,
    name: String,
    species: String,
    status: String
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Column {
            AsyncImage(
                model = image,
                contentDescription = name,
                modifier = Modifier
                    .clip(CircleShape)
            )
        }
        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "$species - $status"
            )
        }
    }
}


@Preview
@Composable
private fun CharacterListScreenPreview() {
    val characterDb = CharacterDb()
    NavigationAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CharacterListScreen(
                state = CharacterLIstState(
                    isLoading = false,
                    data = characterDb.getAllCharacters(),
                    hasError = false
                ),
                onClickWhileLoading = { },
                onRetryClick = { },
                onCharacterClick = { },
                onBack = { }
            )
        }
    }
}