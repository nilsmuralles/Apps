package com.uvg.navigationapp.domain.repository

import com.uvg.navigationapp.domain.model.Character

interface CharacterRepository {
    suspend fun getAllCharacters(): List<Character>
    suspend fun getCharacterByID(id: Int): Character
    suspend fun insertAllCharacters()
}