package com.uvg.navigationapp.presentation.appFlow.location.locationDetails

import com.uvg.navigationapp.data.model.Location

data class LocationDetailsState(
    val isLoading: Boolean = false,
    val data: Location? = null,
    val hasError: Boolean = false
)