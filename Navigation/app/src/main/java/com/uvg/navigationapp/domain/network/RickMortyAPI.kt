package com.uvg.navigationapp.domain.network

import com.uvg.navigationapp.data.network.dto.CharacterListDTO
import com.uvg.navigationapp.data.network.dto.LocationListDTO
import com.uvg.navigationapp.domain.network.util.DataError
import com.uvg.navigationapp.domain.network.util.Result

interface RickMortyAPI {
    suspend fun getAllCharacters(): Result<CharacterListDTO, DataError>
    suspend fun getAllLocations(): Result<LocationListDTO, DataError>
}