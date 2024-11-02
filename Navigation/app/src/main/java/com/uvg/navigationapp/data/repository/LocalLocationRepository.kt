package com.uvg.navigationapp.data.repository

import com.uvg.navigationapp.data.local.dao.LocationDAO
import com.uvg.navigationapp.data.local.entity.mapToEntity
import com.uvg.navigationapp.data.local.entity.mapToModel
import com.uvg.navigationapp.data.network.dto.mapToEntity
import com.uvg.navigationapp.data.network.dto.mapToModel
import com.uvg.navigationapp.data.source.LocationDb
import com.uvg.navigationapp.domain.model.Location
import com.uvg.navigationapp.domain.network.RickMortyAPI
import com.uvg.navigationapp.domain.network.util.DataError
import com.uvg.navigationapp.domain.network.util.Result
import com.uvg.navigationapp.domain.repository.LocationRepository

class LocalLocationRepository(
    private val locationDao: LocationDAO,
    private val rickMortyAPI: RickMortyAPI
): LocationRepository {
    override suspend fun getAllLocations(): Result<List<Location>, DataError> {
        when (val result = rickMortyAPI.getAllLocations()) {
            is Result.Error -> {
                val localLocations = locationDao.getAllLocations()
                if (localLocations.isEmpty()) {
                    if(result.error == DataError.NO_INTERNET) {
                        return Result.Error(
                            DataError.NO_INTERNET
                        )
                    } else {
                        return Result.Error(DataError.GENERIC_ERROR)
                    }
                } else {
                    return Result.Success (
                        localLocations.map { it.mapToModel() }
                    )
                }
            }
            is Result.Success -> {
                val remoteLocations = result.data.results
                locationDao.insertAllLocatioins(
                    remoteLocations.map { it.mapToEntity() }
                )
                return Result.Success(
                    remoteLocations.map { it.mapToModel() }
                )
            }
        }
    }

    override suspend fun getLocationByID(id: Int): Location {
        return locationDao.getLocationFromID(id)
    }

}