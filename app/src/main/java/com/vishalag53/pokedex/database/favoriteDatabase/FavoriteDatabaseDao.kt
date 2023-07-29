package com.vishalag53.pokedex.database.favoriteDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity

@Dao
interface FavoriteDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneFavorite(favoriteList: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavorite(favoriteList: List<PokemonEntity>)

    @Query("DELETE FROM pokemon_list_view")
    suspend fun deleteAllFavorite()

    @Query("DELETE FROM pokemon_list_view WHERE name = :name")
    suspend fun deleteOneFavorite(name: String)

    @Query("SELECT * FROM pokemon_list_view")
    suspend fun getAllFavorite(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon_list_view WHERE name = :name")
    suspend fun getOneFavorite(name : String): PokemonEntity?



}