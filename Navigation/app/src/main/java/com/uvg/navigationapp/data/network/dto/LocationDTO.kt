package com.uvg.navigationapp.data.network.dto

import com.uvg.navigationapp.data.local.entity.LocationEntity
import com.uvg.navigationapp.domain.model.Location
import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)

fun LocationDTO.mapToModel(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

fun LocationDTO.mapToEntity(): LocationEntity {
    return LocationEntity (
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}