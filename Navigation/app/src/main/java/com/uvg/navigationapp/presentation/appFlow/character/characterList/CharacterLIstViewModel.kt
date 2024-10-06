package com.uvg.navigationapp.presentation.appFlow.character.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.navigationapp.data.source.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterLIstViewModel(): ViewModel() {
    private val characterDb = CharacterDb()
    private val _uiState: MutableStateFlow<CharacterLIstState> = MutableStateFlow(CharacterLIstState())
    val uiState = _uiState.asStateFlow()

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(4000)
            val characters = characterDb.getAllCharacters()

            _uiState.update { state ->
                state.copy(
                    data = characters,
                    isLoading = false
                )
            }
        }
    }

    fun throwError(){
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