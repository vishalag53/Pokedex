package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.network.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokemonOverviewViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _currentLayoutType = MutableLiveData<PokemonAdapters.LayoutType>()
    val currentLayoutType: LiveData<PokemonAdapters.LayoutType>
        get() = _currentLayoutType

    val allPokemonListViews : LiveData<List<PokemonEntity>> = liveData {
        val pokemonListViews = pokemonRepository.getAllPokemonListViewFromDB()
        emit(pokemonListViews)
    }


    init {
        _currentLayoutType.value = PokemonAdapters.LayoutType.GRID
        viewModelScope.launch (Dispatchers.IO) {
            //pokemonRepository.getPokemonListView()
            //pokemonRepository.deleteAllPokemon()
        }
    }

    private val _navigateToSelectedProperty = MutableLiveData<PokemonEntity>()
    val navigateToSelectedProperty: LiveData<PokemonEntity>
        get() = _navigateToSelectedProperty


    fun displayPropertyDetails(pokemonList: PokemonEntity){
        _navigateToSelectedProperty.value = pokemonList
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }

    fun setCurrentLayoutType(layoutType: PokemonAdapters.LayoutType){
        _currentLayoutType.value = layoutType
    }


}