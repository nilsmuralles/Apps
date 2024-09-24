package com.uvg.navigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.uvg.navigationapp.presentation.character.characterList.CharacterListDestination
import com.uvg.navigationapp.presentation.character.characterList.characterListScreen
import com.uvg.navigationapp.presentation.character.characterList.navigateToCharacterListScreen
import com.uvg.navigationapp.presentation.character.characterProfile.CharacterProfileDestination
import com.uvg.navigationapp.presentation.character.characterProfile.characterProfileScreen
import com.uvg.navigationapp.presentation.character.characterProfile.navigateToCharacterProfileScreen
import com.uvg.navigationapp.presentation.login.LoginDestination
import com.uvg.navigationapp.presentation.login.loginScreen
import com.uvg.navigationapp.presentation.navigation.MainNavigation
import com.uvg.navigationapp.ui.theme.NavigationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
