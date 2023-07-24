package com.vishalag53.pokedex.pokemon.pokemonoverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.network.PokemonRepository
@Suppress("UNCHECKED_CAST")
class PokemonOverviewViewModelFactory(
    private val pokemonRepository: PokemonRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonOverviewViewModel(pokemonRepository) as T
    }
}