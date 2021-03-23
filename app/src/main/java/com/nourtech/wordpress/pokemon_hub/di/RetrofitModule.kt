package com.nourtech.wordpress.pokemon_hub.di

import com.nourtech.wordpress.pokemon_hub.network.PokemonApiServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun providePokemonApiServer(): PokemonApiServer {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiServer::class.java)
    }

    companion object {
        private const val baseUrl = "https://pokeapi.co/api/v2/"
    }
}