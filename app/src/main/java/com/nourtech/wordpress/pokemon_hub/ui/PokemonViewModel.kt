package com.nourtech.wordpress.pokemon_hub.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nourtech.wordpress.pokemon_hub.model.Pokemon
import com.nourtech.wordpress.pokemon_hub.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val pokemonList = MutableLiveData<ArrayList<Pokemon>>()
    val internetState = MutableLiveData<Boolean>()

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)


    @Inject
    @RequiresApi(Build.VERSION_CODES.N)
    fun getPokemons() {
        scope.launch {
            try {
                val result = repository.getPokemons().results

                // fix the url
                result.replaceAll {
                    val url = it.url
                    val pokemonIndex = url.split("/".toRegex())
                    it.url =
                        "https://pokeres.bastionbot.org/images/pokemon/${pokemonIndex[pokemonIndex.size - 2]}.png"
                    it
                }

                withContext(Dispatchers.Main) {
                    pokemonList.value = result
                    internetState.value = true
                }
            } catch (e: Exception) {
                Log.e("no_internet", "no internet", e)
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    internetState.value = false
                }
            }
        }
    }

    fun getFevPokemons(): LiveData<List<Pokemon>> = repository.getFevPokemons()

    fun addPokemon(pokemon: Pokemon) {
        repository.addFevPokemon(pokemon)
    }

    fun deletePokemon(id: Int) {
        repository.deleteFevPokemon(id)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}