package com.uvg.navigationapp.presentation.appFlow.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.navigationapp.data.DataStoreUserPrefs
import com.uvg.navigationapp.dataStore
import com.uvg.navigationapp.domain.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val userPreferences: UserPreferences
): ViewModel() {
    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    init {
        getName()
    }

    private fun getName() {
        viewModelScope.launch {
            _state.update { it.copy(
                name = userPreferences.getValue("name")
            ) }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                ProfileScreenViewModel(
                    userPreferences = DataStoreUserPrefs(application.dataStore)
                )
            }
        }
    }

}