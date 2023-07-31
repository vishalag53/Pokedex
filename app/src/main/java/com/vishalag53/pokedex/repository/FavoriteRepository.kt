package com.vishalag53.pokedex.repository

import com.vishalag53.pokedex.database.favoriteDatabase.FavoriteDatabaseDao
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity
import com.vishalag53.pokedex.network.PokemonApi

class FavoriteRepository(
    private val pokemonApi: PokemonApi,
    private val favoriteDatabaseDao: FavoriteDatabaseDao,
) {

    suspend fun addOnePokemonEntityInFav(pokedexEntity: PokedexEntity){
        favoriteDatabaseDao.insertOneFavorite(pokedexEntity)
    }

    suspend fun deleteOnePokemonEntityInFav(name: String){
        favoriteDatabaseDao.deleteOneFavorite(name)
    }

    suspend fun getAllFavoriteListFromDB(): List<PokedexEntity>{
        return favoriteDatabaseDao.getAllFavorite()
    }

    suspend fun deleteAllFavorite() {
        favoriteDatabaseDao.deleteAllFavorite()
    }

}