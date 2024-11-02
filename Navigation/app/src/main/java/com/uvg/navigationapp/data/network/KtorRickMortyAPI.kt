package com.uvg.navigationapp.data.network

import com.uvg.navigationapp.data.network.dto.CharacterListDTO
import com.uvg.navigationapp.data.network.dto.LocationListDTO
import com.uvg.navigationapp.data.network.util.safeCall
import com.uvg.navigationapp.domain.network.RickMortyAPI
import com.uvg.navigationapp.domain.network.util.DataError
import com.uvg.navigationapp.domain.network.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorRickMortyAPI(
    private val httpClient: HttpClient
): RickMortyAPI {
    override suspend fun getAllCharacters(): Result<CharacterListDTO, DataError> {
        return safeCall<CharacterListDTO> {
            httpClient.get("https://rickandmortyapi.com/api/character")
        }
    }

    override suspend fun getAllLocations(): Result<LocationListDTO, DataError> {
        return safeCall<LocationListDTO> {
            httpClient.get("https://rickandmortyapi.com/api/location")
        }
    }
}