package com.vishalag53.pokedex.database.favoriteDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false )
abstract class FavoriteDatabase : RoomDatabase(){
    abstract fun favoriteDatabaseDao(): FavoriteDatabaseDao
}