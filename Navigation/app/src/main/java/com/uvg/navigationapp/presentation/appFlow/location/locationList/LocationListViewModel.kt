package com.uvg.navigationapp.presentation.appFlow.location.locationList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.navigationapp.data.local.dao.LocationDAO
import com.uvg.navigationapp.data.repository.LocalLocationRepository
import com.uvg.navigationapp.di.Dependencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel(
    private val locationDao: LocationDAO
): ViewModel() {
    private val locationRepository = LocalLocationRepository(
        locationDao = locationDao
    )
    private val _uiState: MutableStateFlow<LocationListState> = MutableStateFlow(LocationListState())
    val uiState = _uiState.asStateFlow()

    init {
        getLocations()
    }

    private fun populateLocations() {
        viewModelScope.launch {
            locationRepository.insertAllLocations()
        }
    }

    fun getLocations() {
        populateLocations()

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            delay(4000)
            val locations = locationRepository.getAllLocations()

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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                LocationListViewModel(
                    locationDao = db.locationDao()
                )
            }
        }
    }
}