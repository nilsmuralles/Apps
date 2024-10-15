package com.uvg.navigationapp.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.navigationapp.R
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

@Composable
fun LoginRoute(
    onLoginClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onNameChange = { viewModel.onEvent(LoginScreenEvent.NameChange(it)) },
        onLoginClick = {
            viewModel.onEvent(LoginScreenEvent.SaveName)
            onLoginClick()
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onLoginClick: () -> Unit,
    onNameChange: (String) -> Unit
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.rick_and_morty_logo),
            contentDescription = "Logo de Rick & Morty",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(350.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 20.dp),
            value = state.name,
            onValueChange = onNameChange,
            placeholder = {
                Text("Nombre")
            }
        )
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Entrar")
        }

    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp)
        ,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Nils Muralles Morales - 23727")
    }
}

@Preview
@Composable
private fun PreviewLoginScreen(){
    NavigationAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LoginRoute(
                onLoginClick = { }
            )
        }
    }
}