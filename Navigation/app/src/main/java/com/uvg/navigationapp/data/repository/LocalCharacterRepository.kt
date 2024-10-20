package com.uvg.navigationapp.data.repository

import com.uvg.navigationapp.data.local.dao.CharacterDAO
import com.uvg.navigationapp.data.local.entity.mapToEntity
import com.uvg.navigationapp.data.local.entity.mapToModel
import com.uvg.navigationapp.data.source.CharacterDb
import com.uvg.navigationapp.domain.model.Character
import com.uvg.navigationapp.domain.repository.CharacterRepository

class LocalCharacterRepository(
    private val characterDao: CharacterDAO
): CharacterRepository {
    override suspend fun getAllCharacters(): List<Character> {
        val localCharacters = characterDao.getAllCharacters()

        return localCharacters.map { characterEntity ->
            characterEntity.mapToModel()
        }
    }

    override suspend fun getCharacterByID(id: Int): Character {
        return characterDao.getCharacterFromID(id)
    }

    override suspend fun insertAllCharacters() {
        val characters = characterDao.getAllCharacters()

        if (characters.isEmpty()) {
            val characterDb = CharacterDb()
            val remoteCharacters = characterDb.getAllCharacters()
            val localCharacters = remoteCharacters.map { character ->
                character.mapToEntity()
            }
            characterDao.insertAllCharacters(localCharacters)
        }
    }
}