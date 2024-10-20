package com.uvg.navigationapp.presentation.appFlow.character.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.navigationapp.data.local.dao.CharacterDAO
import com.uvg.navigationapp.data.repository.LocalCharacterRepository
import com.uvg.navigationapp.di.Dependencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterLIstViewModel(
    private val characterDao: CharacterDAO
): ViewModel() {
    private val characterRepository = LocalCharacterRepository(
        characterDao = characterDao
    )
    private val _uiState: MutableStateFlow<CharacterLIstState> = MutableStateFlow(CharacterLIstState())
    val uiState = _uiState.asStateFlow()

    init {
        getCharacters()
    }

    private fun populateCharacters() {
        viewModelScope.launch {
            characterRepository.insertAllCharacters()
        }
    }

    fun getCharacters() {
        populateCharacters()

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(4000)
            val characters = characterRepository.getAllCharacters()

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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                CharacterLIstViewModel(
                    characterDao = db.characterDao()
                )
            }
        }
    }
}