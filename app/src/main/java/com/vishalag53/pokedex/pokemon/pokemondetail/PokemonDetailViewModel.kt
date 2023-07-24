package com.vishalag53.pokedex.pokemon.pokemondetail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.network.PokemonRepository

class PokemonDetailViewModel(
    private val pokemonEntity: PokemonEntity,
    private val pokemonRepository: PokemonRepository): ViewModel() {

    private val _selectedProperty = MutableLiveData<PokemonEntity>()
    val selectedProperty: LiveData<PokemonEntity>
        get() = _selectedProperty

    init {
        _selectedProperty.value = pokemonEntity
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


}