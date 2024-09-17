package com.uvg.navigationapp.layouts.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

@Composable
private fun ProfileScreen(
    modifier: Modifier,
    onLogOutClick: () -> Unit
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ){
        Spacer(modifier = Modifier.size(30.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ,
            horizontalArrangement = Arrangement.Center
        ){
            AsyncImage(
                model = "https://images.nightcafe.studio/jobs/tBcdmePqbxhV4hezshFI/tBcdmePqbxhV4hezshFI--1--fn7ub.jpg?tr=w-1600,c-at_max",
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProfileElement(
                label = "Nombre",
                value = "Nils Muralles Morales"
            )
            ProfileElement(
                label = "Carné",
                value = "23727"
            )
        }
        Box (
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            OutlinedButton(
                onClick = onLogOutClick
            ) {
                Text(
                    text = "Cerrar Sesión"
                )
            }
        }
    }
}

@Composable
fun ProfileElement(
    label: String,
    value: String
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "$label:",
            textAlign = TextAlign.Left
        )
        Text(
            text = value,
            textAlign = TextAlign.Right
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview(){
    NavigationAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ProfileScreen(
                modifier = Modifier
                    .fillMaxSize(),
                onLogOutClick = { }
            )
        }
    }
}