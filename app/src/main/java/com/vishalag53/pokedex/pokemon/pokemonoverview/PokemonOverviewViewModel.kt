package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.pokemon.network.PokemonRepository
import com.vishalag53.pokedex.response.PokemonInfo
import com.vishalag53.pokedex.response.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokemonOverviewViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    init {
        viewModelScope.launch (Dispatchers.IO) {
            pokemonRepository.getPokemon()
        }
    }

    val pokemon: LiveData<PokemonList>
        get() = pokemonRepository.pokemonLiveData

    val pokemonInfo: LiveData<PokemonInfo>
        get() = pokemonRepository.pokemonInfo

}