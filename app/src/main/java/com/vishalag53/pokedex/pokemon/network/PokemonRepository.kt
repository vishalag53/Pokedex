package com.vishalag53.pokedex.pokemon.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishalag53.pokedex.response.PokemonInfo
import com.vishalag53.pokedex.response.PokemonList

class PokemonRepository(private val pokemonApi: PokemonApi) {

    private val _pokemonLiveData = MutableLiveData<PokemonList>()

    val pokemonLiveData : LiveData<PokemonList>
    get() = _pokemonLiveData

    private val _pokemonInfo = MutableLiveData<PokemonInfo>()

    val pokemonInfo: LiveData<PokemonInfo>
        get() = _pokemonInfo


    suspend fun getPokemon(){
        val resultPokemonList = pokemonApi.getPokemonList()
        if(resultPokemonList.body() != null){
            _pokemonLiveData.postValue(resultPokemonList.body())
        }

        val resultPokemonInfo = pokemonApi.getPokemonInfo()
        if(resultPokemonInfo.body() != null){
            _pokemonInfo.postValue(resultPokemonInfo.body())
        }
    }
}