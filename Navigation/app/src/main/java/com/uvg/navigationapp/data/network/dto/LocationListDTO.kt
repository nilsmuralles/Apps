package com.uvg.navigationapp.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationListDTO(
    val data: List<CharacterDTO>,
    val message: String,
    val status: Int
)
