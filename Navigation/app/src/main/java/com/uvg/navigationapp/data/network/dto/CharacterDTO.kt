package com.uvg.navigationapp.data.network.dto

import com.uvg.navigationapp.data.local.entity.CharacterEntity
import com.uvg.navigationapp.domain.model.Character
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)

fun CharacterDTO.mapToModel(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}


fun CharacterDTO.mapToEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}