package com.vishalag53.pokedex.pokedex.pokedexdetail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity
import com.vishalag53.pokedex.repository.FavoriteRepository
import com.vishalag53.pokedex.repository.PokedexRepository
import kotlinx.coroutines.launch

class PokedexDetailViewModel(
    pokedexEntity: PokedexEntity,
    private val pokedexRepository: PokedexRepository,
    private val favoriteRepository: FavoriteRepository
): ViewModel() {

    private val _selectedProperty = MutableLiveData<PokedexEntity>()
    val selectedProperty: LiveData<PokedexEntity>
        get() = _selectedProperty

    init {
        _selectedProperty.value = pokedexEntity
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

    private val _isFav = MutableLiveData<Boolean>()
    val isFav : LiveData<Boolean>
        get() = _isFav

    fun getFavoriteStatus(name: String){
        viewModelScope.launch{
            _isFav.value = pokedexRepository.getOnePokemonDetail(name)?.isFav
        }
    }

    fun setFavouriteStatus(isFav: Boolean, pokedexEntity: PokedexEntity){
        _isFav.value = isFav
        if(isFav){
            viewModelScope.launch {
                favoriteRepository.addOnePokemonEntityInFav(pokedexEntity)
                pokedexRepository.updatePokemonFav(pokedexEntity.name,true)
            }
        }
        else{
            viewModelScope.launch {
                favoriteRepository.deleteOnePokemonEntityInFav(pokedexEntity.name)
                pokedexRepository.updatePokemonFav(pokedexEntity.name,false)
            }
        }
    }

}