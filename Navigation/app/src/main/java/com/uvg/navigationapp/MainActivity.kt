package com.uvg.navigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.uvg.navigationapp.layouts.characterList.CharacterListDestination
import com.uvg.navigationapp.layouts.characterList.CharacterListScreen
import com.uvg.navigationapp.layouts.characterList.characterListScreen
import com.uvg.navigationapp.layouts.characterList.navigateToCharacterListScreen
import com.uvg.navigationapp.layouts.characterProfile.CharacterProfileDestination
import com.uvg.navigationapp.layouts.characterProfile.characterProfileScreen
import com.uvg.navigationapp.layouts.characterProfile.navigateToCharacterProfileScreen
import com.uvg.navigationapp.layouts.login.LoginDestination
import com.uvg.navigationapp.layouts.login.LoginScreen
import com.uvg.navigationapp.layouts.login.loginScreen
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = LoginDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        loginScreen (
                            onLoginClick = {
                                navController.navigateToCharacterListScreen(
                                    destination = CharacterListDestination,
                                    navOptions = navOptions {
                                        popUpTo<LoginDestination>() {
                                            inclusive = true
                                        }
                                    }
                                )
                            }
                        )
                        characterListScreen(
                            onCharacterClick = { id ->
                                navController.navigateToCharacterProfileScreen(
                                    destination = CharacterProfileDestination(
                                        charID = id
                                    )
                                )
                            },
                            onBack = {
                                navController.navigateUp()
                            }
                        )
                        characterProfileScreen(
                            onBack = {
                                navController.navigateToCharacterListScreen(
                                    destination = CharacterListDestination,
                                    navOptions = navOptions {
                                        popUpTo(0)
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
