package com.uvg.navigationapp.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationListDTO(
    val results: List<LocationDTO>
)
