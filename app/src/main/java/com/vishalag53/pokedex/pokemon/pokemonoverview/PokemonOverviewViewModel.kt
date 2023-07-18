package com.vishalag53.pokedex.pokemon.pokemonoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.network.PokemonRepository
import com.vishalag53.pokedex.response.PokemonInfo
import com.vishalag53.pokedex.response.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokemonOverviewViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _view = MutableLiveData<List<PokemonView>>()
    val view : LiveData<List<PokemonView>>
        get() = _view



    init {
        viewModelScope.launch (Dispatchers.IO) {
            pokemonRepository.getPokemonListView()
        }
    }

    val pokemonListView: LiveData<List<PokemonListView>>
        get() = pokemonRepository.pokemonListView

    val pokemon: LiveData<PokemonList>
        get() = pokemonRepository.pokemonLiveData

}