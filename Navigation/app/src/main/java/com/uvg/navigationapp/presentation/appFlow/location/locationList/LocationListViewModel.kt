package com.uvg.navigationapp.presentation.appFlow.location.locationList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.navigationapp.data.source.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel(): ViewModel() {
    private val locationDb = LocationDb()
    private val _uiState: MutableStateFlow<LocationListState> = MutableStateFlow(LocationListState())
    val uiState = _uiState.asStateFlow()

    init {
        getLocations()
    }

    fun getLocations() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(4000)
            val locations = locationDb.getAllLocations()

            _uiState.update { state ->
                state.copy(
                    data = locations,
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