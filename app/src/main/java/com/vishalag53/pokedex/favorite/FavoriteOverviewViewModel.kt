package com.vishalag53.pokedex.favorite

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.database.favoriteDatabase.FavoriteDatabaseDao
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.network.PokemonRepository
import kotlinx.coroutines.launch

class FavoriteOverviewViewModel(
    private val pokemonRepository: PokemonRepository,
    private val daoDatabaseFavorite: FavoriteDatabaseDao
) : ViewModel() {
    private val _currentLayoutType = MutableLiveData<FavoriteAdapters.LayoutType>()
    val currentLayoutType: LiveData<FavoriteAdapters.LayoutType>
        get() = _currentLayoutType

    val allFavoritePokemonListViews : LiveData<List<PokemonEntity>> = liveData {
        val favoritePokemonListViews = pokemonRepository.getAllFavoriteListFromDB()
        emit(favoritePokemonListViews)
    }


    init {
        _currentLayoutType.value = FavoriteAdapters.LayoutType.GRID
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

    fun setCurrentLayoutType(layoutType: FavoriteAdapters.LayoutType){
        _currentLayoutType.value = layoutType
    }

    private val _favoriteList = MutableLiveData<List<PokemonEntity>>()
    val favoriteList: LiveData<List<PokemonEntity>>
        get() = _favoriteList

    fun loadFavoriteFromDB(){
        viewModelScope.launch {
            val favorites = daoDatabaseFavorite.getAllFavorite()
            _favoriteList.postValue(favorites)
        }
    }

    fun deleteAllFavorites(){
        viewModelScope.launch {
            pokemonRepository.getAllFavoriteListFromDB().forEach {
                pokemonRepository.updatePokemonFav(it.name,false)
            }
            pokemonRepository.deleteAllFavorite()
            _favoriteList.postValue(emptyList())
        }
    }
}