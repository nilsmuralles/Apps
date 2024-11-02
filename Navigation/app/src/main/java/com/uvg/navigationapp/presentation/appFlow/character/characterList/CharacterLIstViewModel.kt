package com.uvg.navigationapp.presentation.appFlow.character.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.navigationapp.data.local.dao.CharacterDAO
import com.uvg.navigationapp.data.network.KtorRickMortyAPI
import com.uvg.navigationapp.data.repository.LocalCharacterRepository
import com.uvg.navigationapp.di.Dependencies
import com.uvg.navigationapp.domain.network.util.DataError
import com.uvg.navigationapp.domain.network.util.onError
import com.uvg.navigationapp.domain.network.util.onSuccess
import com.uvg.navigationapp.domain.repository.CharacterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterLIstViewModel(
    private val characterRepository: CharacterRepository
): ViewModel() {
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

            characterRepository.getAllCharacters()
                .onSuccess { characters ->
                    _uiState.update { state ->
                        state.copy(
                            data = characters,
                            isLoading = false,
                            hasError = false
                        )
                    }
                }
                .onError { error -> throwError(error) }
        }
    }

    fun throwError(error: DataError){
        _uiState.update { state ->
            state.copy(
                hasError = true,
                isLoading = false
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                val api = KtorRickMortyAPI(Dependencies.provideHttpClient())
                CharacterLIstViewModel(
                    characterRepository = LocalCharacterRepository(
                        rickMortyAPI = api,
                        characterDao = db.characterDao()
                    )
                )
            }
        }
    }
}