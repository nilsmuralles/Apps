package com.uvg.navigationapp.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uvg.navigationapp.data.local.entity.LocationEntity
import com.uvg.navigationapp.domain.model.Location

@Dao
interface LocationDAO {
    @Query("SELECT * FROM LocationEntity")
    suspend fun getAllLocations(): List<LocationEntity>
    @Insert
    suspend fun insertAllLocatioins(locations: List<LocationEntity>)
    @Query("SELECT * FROM LocationEntity WHERE id = :locationID")
    fun getLocationFromID(locationID: Int): Location
}