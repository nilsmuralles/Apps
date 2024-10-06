package com.uvg.navigationapp.presentation.appFlow.location.locationList

import com.uvg.navigationapp.data.model.Location

data class LocationListState(
    val isLoading: Boolean = false,
    val data: List<Location>? = null,
    val hasError: Boolean = false
)
