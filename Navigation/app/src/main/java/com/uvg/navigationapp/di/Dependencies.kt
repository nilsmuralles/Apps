package com.uvg.navigationapp.di

import android.content.Context
import androidx.room.Room
import com.uvg.navigationapp.data.local.RickAndMortyDB
import com.uvg.navigationapp.data.network.HttpClientFactory
import io.ktor.client.HttpClient

object Dependencies {
    private var database: RickAndMortyDB? = null
    private var httpClient: HttpClient? = null

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    private fun buildDatabase(context: Context): RickAndMortyDB {
        return Room.databaseBuilder(
            context,
            RickAndMortyDB::class.java,
            "rickandmorty.db"
        ).build()
    }

    fun provideDatabase(context: Context): RickAndMortyDB {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }

    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }
}