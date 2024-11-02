package com.uvg.navigationapp.presentation.appFlow.location.locationList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.navigationapp.data.local.dao.LocationDAO
import com.uvg.navigationapp.data.network.KtorRickMortyAPI
import com.uvg.navigationapp.data.repository.LocalLocationRepository
import com.uvg.navigationapp.di.Dependencies
import com.uvg.navigationapp.domain.network.util.DataError
import com.uvg.navigationapp.domain.network.util.onError
import com.uvg.navigationapp.domain.network.util.onSuccess
import com.uvg.navigationapp.domain.repository.LocationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel(
    private val locationRepository: LocationRepository
): ViewModel() {
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

            locationRepository.getAllLocations()
                .onSuccess { locations ->
                    _uiState.update { state ->
                        state.copy(
                            data = locations,
                            isLoading = false
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
                LocationListViewModel(
                    locationRepository = LocalLocationRepository(
                        rickMortyAPI = api,
                        locationDao = db.locationDao()
                    )
                )
            }
        }
    }
}