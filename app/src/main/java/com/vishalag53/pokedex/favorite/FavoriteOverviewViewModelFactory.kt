package com.vishalag53.pokedex.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.database.favoriteDatabase.FavoriteDatabaseDao
import com.vishalag53.pokedex.repository.FavoriteRepository
import com.vishalag53.pokedex.repository.PokedexRepository

@Suppress("UNCHECKED_CAST")
class FavoriteOverviewViewModelFactory(
    private val pokedexRepository: PokedexRepository,
    private val favoriteRepository: FavoriteRepository,
    private val dao: FavoriteDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteOverviewViewModel(pokedexRepository,favoriteRepository,dao) as T
    }
}