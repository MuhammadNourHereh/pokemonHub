package com.nourtech.wordpress.pokemon_hub.db;

import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query

import com.nourtech.wordpress.pokemon_hub.model.Pokemon;

@Dao
interface PokemonDao {

    @Insert
    fun insertPokemon(pokemon: Pokemon)

    @Query("SELECT * FROM table_fev ")
    fun getAll(): LiveData<List<Pokemon>>

    @Query("DELETE  FROM table_fev WHERE id = :id")
    fun delete(id: Int)

}
