package com.uvg.navigationapp.domain.repository

import com.uvg.navigationapp.domain.model.Location
import com.uvg.navigationapp.domain.network.util.DataError
import com.uvg.navigationapp.domain.network.util.Result

interface LocationRepository {
    suspend fun getAllLocations(): Result<List<Location>, DataError>
    suspend fun getLocationByID(id: Int): Location
}