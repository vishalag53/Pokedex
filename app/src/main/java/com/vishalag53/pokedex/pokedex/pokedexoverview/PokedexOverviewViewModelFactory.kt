package com.vishalag53.pokedex.pokedex.pokedexoverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.repository.PokedexRepository
@Suppress("UNCHECKED_CAST")
class PokedexOverviewViewModelFactory(
    private val pokedexRepository: PokedexRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokedexOverviewViewModel(pokedexRepository) as T
    }
}