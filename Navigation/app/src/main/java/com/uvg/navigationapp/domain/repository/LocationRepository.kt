package com.uvg.navigationapp.domain.repository

import com.uvg.navigationapp.domain.model.Location

interface LocationRepository {
    suspend fun getAllLocations(): List<Location>
    suspend fun getLocationByID(id: Int): Location
    suspend fun insertAllLocations()
}