package com.uvg.navigationapp.layouts.characterProfile

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uvg.navigationapp.CharacterDb
import com.uvg.navigationapp.layouts.characterList.CustomTopBar
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

val charDB = CharacterDb()

@Composable
fun CharacterProfileRoute(
    modifier: Modifier,
    charID: Int,
    onBack: () -> Unit
){
    val character = charDB.getCharacterById(charID)
    CharacterProfileScreen(
        modifier = Modifier
            .fillMaxSize(),
        image = character.image,
        name = character.name,
        species = character.species,
        status = character.status,
        gender = character.gender,
        onBack = onBack
    )
}

@Composable
private fun CharacterProfileScreen(
    modifier: Modifier,
    image: String,
    name: String,
    species: String,
    status: String,
    gender: String,
    onBack: () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        CustomTopBar(
            title = "Character Detail",
            onBack = onBack
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ,
            horizontalArrangement = Arrangement.Center
        ){
            AsyncImage(
                model = image,
                contentDescription = name,
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
                text = name,
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
                Text(text = species)
                Text(text = status)
                Text(text = gender)
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
                modifier = Modifier,
                image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                name = "Morty Smith",
                species = "Human",
                status = "Alive",
                gender = "Male",
                onBack = { }
            )
        }
    }
}