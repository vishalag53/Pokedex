package com.vishalag53.pokedex.favorite

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.database.favoriteDatabase.FavoriteDatabaseDao
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity
import com.vishalag53.pokedex.repository.FavoriteRepository
import com.vishalag53.pokedex.repository.PokedexRepository
import kotlinx.coroutines.launch

class FavoriteOverviewViewModel(
    private val pokedexRepository: PokedexRepository,
    private val favoriteRepository: FavoriteRepository,
    private val daoDatabaseFavorite: FavoriteDatabaseDao
) : ViewModel() {
    private val _currentLayoutType = MutableLiveData<FavoriteAdapters.LayoutType>()
    val currentLayoutType: LiveData<FavoriteAdapters.LayoutType>
        get() = _currentLayoutType

    val allFavoritePokemonListViews : LiveData<List<PokedexEntity>> = liveData {
        val favoritePokemonListViews = favoriteRepository.getAllFavoriteListFromDB()
        emit(favoritePokemonListViews)
    }


    init {
        _currentLayoutType.value = FavoriteAdapters.LayoutType.GRID
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

    fun setCurrentLayoutType(layoutType: FavoriteAdapters.LayoutType){
        _currentLayoutType.value = layoutType
    }

    private val _favoriteList = MutableLiveData<List<PokedexEntity>>()
    val favoriteList: LiveData<List<PokedexEntity>>
        get() = _favoriteList

    fun loadFavoriteFromDB(){
        viewModelScope.launch {
            val favorites = daoDatabaseFavorite.getAllFavorite()
            _favoriteList.postValue(favorites)
        }
    }

    fun deleteAllFavorites(){
        viewModelScope.launch {
            favoriteRepository.getAllFavoriteListFromDB().forEach {
                pokedexRepository.updatePokemonFav(it.name,false)
            }
            favoriteRepository.deleteAllFavorite()
            _favoriteList.postValue(emptyList())
        }
    }
}