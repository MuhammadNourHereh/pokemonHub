package com.nourtech.wordpress.pokemon_hub.repository

import androidx.lifecycle.LiveData
import com.nourtech.wordpress.pokemon_hub.db.PokemonDao
import com.nourtech.wordpress.pokemon_hub.model.Pokemon
import com.nourtech.wordpress.pokemon_hub.model.PokemonResponse
import com.nourtech.wordpress.pokemon_hub.network.PokemonApiServer
import javax.inject.Inject

class Repository @Inject constructor(private var pokemonApiServer: PokemonApiServer,private var dao:PokemonDao) {

    suspend fun getPokemons(): PokemonResponse {
        return pokemonApiServer.getPokemon()
    }

    fun getFevPokemons(): LiveData<List<Pokemon>>{
        return dao.getAll()
    }

    fun deleteFevPokemon(id:Int){
        dao.delete(id)
    }

    fun addFevPokemon(pokemon:Pokemon){
        dao.insertPokemon(pokemon)
    }
}
