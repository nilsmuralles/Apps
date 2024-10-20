package com.uvg.navigationapp.data.repository

import com.uvg.navigationapp.data.local.dao.LocationDAO
import com.uvg.navigationapp.data.local.entity.mapToEntity
import com.uvg.navigationapp.data.local.entity.mapToModel
import com.uvg.navigationapp.data.source.LocationDb
import com.uvg.navigationapp.domain.model.Location
import com.uvg.navigationapp.domain.repository.LocationRepository

class LocalLocationRepository(
    private val locationDao: LocationDAO
): LocationRepository {
    override suspend fun getAllLocations(): List<Location> {
        val localLocations = locationDao.getAllLocations()

        return localLocations.map { location ->
            location.mapToModel()
        }
    }

    override suspend fun getLocationByID(id: Int): Location {
        return locationDao.getLocationFromID(id)
    }

    override suspend fun insertAllLocations() {
        val locations = locationDao.getAllLocations()

        if (locations.isEmpty()) {
            val locationDb = LocationDb()
            val remoteLocations = locationDb.getAllLocations()
            val localLocations = remoteLocations.map { localLocation ->
                localLocation.mapToEntity()
            }
            locationDao.insertAllLocatioins(localLocations)
        }
    }
}