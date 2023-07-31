package com.vishalag53.pokedex.database.favoriteDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity
import com.vishalag53.pokedex.util.TypeConverter

@Database(entities = [PokedexEntity::class], version = 3, exportSchema = false )
@TypeConverters(TypeConverter::class)
abstract class FavoriteDatabase : RoomDatabase(){
    abstract fun favoriteDatabaseDao(): FavoriteDatabaseDao
}