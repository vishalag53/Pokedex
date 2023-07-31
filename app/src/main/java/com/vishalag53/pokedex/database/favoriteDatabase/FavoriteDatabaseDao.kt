package com.vishalag53.pokedex.database.favoriteDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity

@Dao
interface FavoriteDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneFavorite(favoriteList: PokedexEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavorite(favoriteList: List<PokedexEntity>)

    @Query("DELETE FROM pokedex_list_view")
    suspend fun deleteAllFavorite()

    @Query("DELETE FROM pokedex_list_view WHERE name = :name")
    suspend fun deleteOneFavorite(name: String)

    @Query("SELECT * FROM pokedex_list_view")
    suspend fun getAllFavorite(): List<PokedexEntity>

    @Query("SELECT * FROM pokedex_list_view WHERE name = :name")
    suspend fun getOneFavorite(name : String): PokedexEntity?



}