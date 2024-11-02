package com.uvg.navigationapp.data.repository

import com.uvg.navigationapp.data.local.dao.CharacterDAO
import com.uvg.navigationapp.data.local.entity.mapToEntity
import com.uvg.navigationapp.data.local.entity.mapToModel
import com.uvg.navigationapp.data.network.dto.mapToEntity
import com.uvg.navigationapp.data.network.dto.mapToModel
import com.uvg.navigationapp.data.source.CharacterDb
import com.uvg.navigationapp.domain.model.Character
import com.uvg.navigationapp.domain.network.RickMortyAPI
import com.uvg.navigationapp.domain.network.util.DataError
import com.uvg.navigationapp.domain.network.util.Result
import com.uvg.navigationapp.domain.network.util.onError
import com.uvg.navigationapp.domain.network.util.onSuccess
import com.uvg.navigationapp.domain.repository.CharacterRepository

class LocalCharacterRepository(
    private val characterDao: CharacterDAO,
    private val rickMortyAPI: RickMortyAPI
): CharacterRepository {
    override suspend fun getAllCharacters(): Result<List<Character>, DataError> {
        when (val result = rickMortyAPI.getAllCharacters()) {
            is Result.Error -> {
                val localCharacters = characterDao.getAllCharacters()
                if (localCharacters.isEmpty()) {
                    if(result.error == DataError.NO_INTERNET) {
                        return Result.Error(
                            DataError.NO_INTERNET
                        )
                    } else {
                        return Result.Error(DataError.GENERIC_ERROR)
                    }

                } else {
                    return Result.Success(
                        localCharacters.map { it.mapToModel() }
                    )
                }
            }
            is Result.Success -> {
                val remoteCharacter = result.data.results
                characterDao.insertAllCharacters(
                    remoteCharacter.map { it.mapToEntity() }
                )
                return Result.Success(
                    remoteCharacter.map { it.mapToModel() }
                )
            }
        }
    }

    override suspend fun getCharacterByID(id: Int): Character {
        return characterDao.getCharacterFromID(id)
    }
}