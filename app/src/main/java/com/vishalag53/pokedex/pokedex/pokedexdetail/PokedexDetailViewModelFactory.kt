package com.vishalag53.pokedex.pokedex.pokedexdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity
import com.vishalag53.pokedex.repository.FavoriteRepository
import com.vishalag53.pokedex.repository.PokedexRepository

class PokedexDetailViewModelFactory(
    private val pokedexEntity: PokedexEntity,
    private val pokedexRepository: PokedexRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokedexDetailViewModel::class.java)) {
            return PokedexDetailViewModel(pokedexEntity,pokedexRepository,favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}