package com.vishalag53.pokedex.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishalag53.pokedex.pokemon.pokemonoverview.PokemonView
import com.vishalag53.pokedex.pokemon.pokemonoverview.PokemonListView
import com.vishalag53.pokedex.response.PokemonInfo
import com.vishalag53.pokedex.response.PokemonList


class PokemonRepository(private val pokemonApi: PokemonApi) {

    private val _pokemonListView = MutableLiveData<List<PokemonListView>>()
    val pokemonListView: LiveData<List<PokemonListView>>
        get() = _pokemonListView

    suspend fun getPokemonListView(){
        val pokemonListResponse = pokemonApi.getPokemonList()
        val pokemonList = pokemonListResponse.body()?.results ?: emptyList()

        val pokemonListViewList = mutableListOf<PokemonListView>()

        for (pokemon in pokemonList) {
            val pokemonInfoResponse = pokemonApi.getPokemonInfo(pokemon.name)
            val pokemonInfo = pokemonInfoResponse.body()
            if (pokemonInfo != null) {
                val pokemonView = PokemonView(
                    pokemonInfo.sprites.front_default,
                    pokemonInfo.id.toString(),
                    pokemonInfo.name,
                    pokemonInfo.types.getOrNull(0)?.type?.name,
                    pokemonInfo.types.getOrNull(1)?.type?.name
                )
                val pokemonListView = PokemonListView(pokemonView)
                pokemonListViewList.add(pokemonListView)
            }
        }
        _pokemonListView.postValue(pokemonListViewList)
    }


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
    }


    suspend fun getPokemonInfo(name:String){
        val resultPokemonInfo = pokemonApi.getPokemonInfo(name)
        if(resultPokemonInfo.body() != null){
            _pokemonInfo.postValue(resultPokemonInfo.body())
        }
    }

}