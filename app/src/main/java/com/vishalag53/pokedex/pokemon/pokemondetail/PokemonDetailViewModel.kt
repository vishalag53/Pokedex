package com.vishalag53.pokedex.pokemon.pokemondetail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.network.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    private val _isFav = MutableLiveData<Boolean>()
    val isFav : LiveData<Boolean>
        get() = _isFav

    fun getFavoriteStatus(name: String){
        viewModelScope.launch{
            _isFav.value = pokemonRepository.getOnePokemonDetail(name)?.isFav
        }
    }

    fun setFavouriteStatus(isFav: Boolean, pokemonEntity: PokemonEntity){
        _isFav.value = isFav
        if(isFav){
            viewModelScope.launch {
                pokemonRepository.addOnePokemonEntityInFav(pokemonEntity)
                pokemonRepository.updatePokemonFav(pokemonEntity.name,isFav)
            }
        }
        else{
            viewModelScope.launch {
                pokemonRepository.deleteOnePokemonEntityInFav(pokemonEntity.name)
                pokemonRepository.updatePokemonFav(pokemonEntity.name,isFav)
            }
        }
    }

}