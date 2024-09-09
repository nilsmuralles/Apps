package com.uvg.navigationapp.layouts.characterList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uvg.navigationapp.Character
import com.uvg.navigationapp.CharacterDb
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

val charDB = CharacterDb()

@Composable
fun CharacterListRoute(
    modifier: Modifier,
    onCharacterClick: (Int) -> Unit,
    onBack: () -> Unit
){
    CharacterListScreen(
        characters = charDB.getAllCharacters(),
        onCharacterClick = onCharacterClick,
        onBack = onBack
    )
}

@Composable
fun CharacterListScreen(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit,
    onBack: () -> Unit
){
    Column {
        CustomTopBar(
            title = "Characters",
            onBack = onBack
        )
        LazyColumn {
            itemsIndexed(characters) { index, character ->
                Character(
                    image = character.image,
                    name = character.name,
                    species = character.species,
                    status = character.status,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCharacterClick(character.id) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    onBack: () -> Unit
){
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
            .clickable {  },
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
    val characters = rememberSaveable {
        charDB.getAllCharacters()
    }
    NavigationAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CharacterListScreen(
                characters = characters,
                onCharacterClick = { },
                onBack = { }
            )
        }
    }
}