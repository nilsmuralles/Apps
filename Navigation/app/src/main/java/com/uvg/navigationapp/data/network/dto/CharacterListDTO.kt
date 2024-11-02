package com.uvg.navigationapp.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterListDTO(
    val results: List<CharacterDTO>
)
