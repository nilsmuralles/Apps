package com.uvg.navigationapp.di

import android.content.Context
import androidx.room.Room
import com.uvg.navigationapp.data.local.RickAndMortyDB

object Dependencies {
    private var database: RickAndMortyDB? = null

    private fun buildDatabase(context: Context): RickAndMortyDB {
        return Room.databaseBuilder(
            context,
            RickAndMortyDB::class.java,
            "uvg.db"
        ).build()
    }

    fun provideDatabase(context: Context): RickAndMortyDB {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }
}