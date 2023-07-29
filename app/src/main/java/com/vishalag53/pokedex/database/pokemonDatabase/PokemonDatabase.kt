package com.vishalag53.pokedex.database.pokemonDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vishalag53.pokedex.util.TypeConverter


@Database(entities = [PokemonEntity::class], version = 6, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class PokemonDatabase : RoomDatabase(){
    abstract fun pokemonDatabaseDao() : PokemonDatabaseDao
}