package com.uvg.navigationapp.presentation.appFlow.location.locationDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.uvg.navigationapp.data.source.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationDetailsViewModel (
    savedStateHandle: SavedStateHandle
): ViewModel()
{
    private val locationDb = LocationDb()
    private val locationDetails = savedStateHandle.toRoute<LocationDetailsDestination>()
    private val _uiState: MutableStateFlow<LocationDetailsState> = MutableStateFlow(LocationDetailsState())
    val uiState = _uiState.asStateFlow()

    init {
        getLocationData()
    }

    fun getLocationData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(2000)
            val location = locationDb.getLocationById(
                locationDetails.locationID
            )

            _uiState.update { state ->
                state.copy(
                    data = location,
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