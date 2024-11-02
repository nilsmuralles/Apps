package com.uvg.navigationapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.uvg.navigationapp.data.local.entity.CharacterEntity
import com.uvg.navigationapp.domain.model.Character

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): List<CharacterEntity>
    @Upsert
    suspend fun insertAllCharacters(characters: List<CharacterEntity>)
    @Query("SELECT * FROM CharacterEntity WHERE id = :characterID")
    fun getCharacterFromID(characterID: Int): Character
}