package com.vishalag53.pokedex.pokedex.pokedexoverview

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity
import com.vishalag53.pokedex.repository.PokedexRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokedexOverviewViewModel(private val pokedexRepository: PokedexRepository) : ViewModel() {

    private val _currentLayoutType = MutableLiveData<PokedexAdapters.LayoutType>()
    val currentLayoutType: LiveData<PokedexAdapters.LayoutType>
        get() = _currentLayoutType

    val allPokemonListViews : LiveData<List<PokedexEntity>> = liveData {
        val pokemonListViews = pokedexRepository.getAllPokemonListViewFromDB()
        emit(pokemonListViews)
    }


    init {
        _currentLayoutType.value = PokedexAdapters.LayoutType.GRID
        viewModelScope.launch (Dispatchers.IO) {
            //pokedexRepository.getPokemonListView()
            //pokedexRepository.deleteAllPokemon()
        }
    }

    private val _navigateToSelectedProperty = MutableLiveData<PokedexEntity>()
    val navigateToSelectedProperty: LiveData<PokedexEntity>
        get() = _navigateToSelectedProperty


    fun displayPropertyDetails(pokemonList: PokedexEntity){
        _navigateToSelectedProperty.value = pokemonList
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }

    fun setCurrentLayoutType(layoutType: PokedexAdapters.LayoutType){
        _currentLayoutType.value = layoutType
    }

}