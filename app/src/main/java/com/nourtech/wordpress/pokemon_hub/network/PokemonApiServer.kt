package com.nourtech.wordpress.pokemon_hub.network

import com.nourtech.wordpress.pokemon_hub.model.PokemonResponse
import retrofit2.http.GET


interface PokemonApiServer {

    @GET("pokemon")
    suspend fun getPokemon(): PokemonResponse

}