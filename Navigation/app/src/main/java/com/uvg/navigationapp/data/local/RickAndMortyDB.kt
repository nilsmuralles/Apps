package com.uvg.navigationapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uvg.navigationapp.data.local.dao.CharacterDAO
import com.uvg.navigationapp.data.local.dao.LocationDAO
import com.uvg.navigationapp.data.local.entity.CharacterEntity
import com.uvg.navigationapp.data.local.entity.LocationEntity

@Database(
    entities = [
        LocationEntity::class,
        CharacterEntity::class
    ],
    version = 1
)
abstract class RickAndMortyDB: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
    abstract fun locationDao(): LocationDAO
}