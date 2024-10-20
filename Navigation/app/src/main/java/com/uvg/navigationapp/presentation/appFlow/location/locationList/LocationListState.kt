package com.uvg.navigationapp.presentation.appFlow.location.locationList

import com.uvg.navigationapp.domain.model.Location

data class LocationListState(
    val isLoading: Boolean = false,
    val data: List<Location>? = null,
    val hasError: Boolean = false
)
