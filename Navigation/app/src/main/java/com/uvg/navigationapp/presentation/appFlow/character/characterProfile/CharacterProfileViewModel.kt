package com.uvg.navigationapp.presentation.appFlow.character.characterProfile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.uvg.navigationapp.data.source.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterProfileViewModel (
    savedStateHandle: SavedStateHandle
):ViewModel()
{
    private val characterDb = CharacterDb()
    private val characterProfile = savedStateHandle.toRoute<CharacterProfileDestination>()
    private val _uiState: MutableStateFlow<CharacterProfileState> = MutableStateFlow(CharacterProfileState())
    val uiState = _uiState.asStateFlow()

    init {
        getCharacterData()
    }

    fun getCharacterData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(2000)
            val character = characterDb.getCharacterById(characterProfile.charID)

            _uiState.update { state ->
                state.copy(
                    data = character,
                    isLoading = false
                )
            }
        }
    }

    fun throwError() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    hasError = true,
                    isLoading = false
                )
            }
        }
    }
}