package com.uvg.navigationapp.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)
