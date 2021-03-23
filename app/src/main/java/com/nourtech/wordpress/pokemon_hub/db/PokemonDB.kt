package com.nourtech.wordpress.pokemon_hub.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nourtech.wordpress.pokemon_hub.model.Pokemon


@Database(entities = [Pokemon::class],version = 1,exportSchema = false)
abstract class PokemonDB: RoomDatabase() {
        abstract fun pokemonDao(): PokemonDao
}