package com.uvg.navigationapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun setName(name: String)
    suspend fun getValue(key: String): String?
    fun authStatus(): Flow<Boolean>
    suspend fun logIn()
    suspend fun logOut()
}