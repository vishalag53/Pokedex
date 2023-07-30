package com.vishalag53.pokedex.pokemon.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.repository.FavoriteRepository
import com.vishalag53.pokedex.repository.PokemonRepository

class PokemonDetailViewModelFactory(
    private val pokemonEntity: PokemonEntity,
    private val pokemonRepository: PokemonRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            return PokemonDetailViewModel(pokemonEntity,pokemonRepository,favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}