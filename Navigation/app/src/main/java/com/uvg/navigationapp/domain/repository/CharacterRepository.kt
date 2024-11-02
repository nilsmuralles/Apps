package com.uvg.navigationapp.domain.repository

import com.uvg.navigationapp.domain.model.Character
import com.uvg.navigationapp.domain.network.util.DataError
import com.uvg.navigationapp.domain.network.util.Result

interface CharacterRepository {
    suspend fun getAllCharacters(): Result<List<Character>, DataError>
    suspend fun getCharacterByID(id: Int): Character
}