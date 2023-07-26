package com.vishalag53.pokedex.database.favoriteDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneFavorite(favoriteList: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavorite(favoriteList: List<FavoriteEntity>)

    @Query("DELETE FROM favorite_list_view")
    suspend fun deleteAllFavorite()

    @Query("DELETE FROM favorite_list_view WHERE name = :name")
    suspend fun deleteOneFavorite(name: String)

    @Query("SELECT * FROM favorite_list_view")
    suspend fun getAllFavorite(): List<FavoriteEntity>

    @Query("SELECT * FROM favorite_list_view WHERE name = :name")
    suspend fun getOneFavorite(name : String): FavoriteEntity?

}