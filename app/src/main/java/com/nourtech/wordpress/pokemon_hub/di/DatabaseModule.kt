package com.nourtech.wordpress.pokemon_hub.di

import android.app.Application
import androidx.room.Room
import com.nourtech.wordpress.pokemon_hub.db.PokemonDB
import com.nourtech.wordpress.pokemon_hub.db.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(application: Application): PokemonDB{
        return Room.databaseBuilder(application, PokemonDB::class.java, "table_fev")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun getDao(pokemonDB: PokemonDB): PokemonDao{
        return pokemonDB.pokemonDao()
    }
}