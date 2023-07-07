package com.vishalag53.pokedex.pokemon.pokemondetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.pokemon.network.PokemonProperty

class PokemonDetailViewModelFactory (
    private val pokemonProperty: PokemonProperty,
    private val application: Application
        ) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            return PokemonDetailViewModel(pokemonProperty,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}