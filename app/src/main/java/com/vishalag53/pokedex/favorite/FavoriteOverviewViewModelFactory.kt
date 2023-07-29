package com.vishalag53.pokedex.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.database.favoriteDatabase.FavoriteDatabaseDao
import com.vishalag53.pokedex.network.PokemonRepository

class FavoriteOverviewViewModelFactory(
    private val pokemonRepository: PokemonRepository,
    private val dao: FavoriteDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteOverviewViewModel(pokemonRepository,dao) as T
    }
}