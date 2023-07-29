package com.vishalag53.pokedex.database.favoriteDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.util.TypeConverter

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false )
@TypeConverters(TypeConverter::class)
abstract class FavoriteDatabase : RoomDatabase(){
    abstract fun favoriteDatabaseDao(): FavoriteDatabaseDao
}