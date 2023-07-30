package com.vishalag53.pokedex.repository

import com.vishalag53.pokedex.database.favoriteDatabase.FavoriteDatabaseDao
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.network.PokemonApi

class FavoriteRepository(
    private val pokemonApi: PokemonApi,
    private val favoriteDatabaseDao: FavoriteDatabaseDao,
) {

    suspend fun addOnePokemonEntityInFav(pokemonEntity: PokemonEntity){
        favoriteDatabaseDao.insertOneFavorite(pokemonEntity)
    }

    suspend fun deleteOnePokemonEntityInFav(name: String){
        favoriteDatabaseDao.deleteOneFavorite(name)
    }

    suspend fun getAllFavoriteListFromDB(): List<PokemonEntity>{
        return favoriteDatabaseDao.getAllFavorite()
    }

    suspend fun deleteAllFavorite() {
        favoriteDatabaseDao.deleteAllFavorite()
    }

}