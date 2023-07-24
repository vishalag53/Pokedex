package com.vishalag53.pokedex.database.pokemonDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [PokemonEntity::class], version = 4, exportSchema = false)
@TypeConverters(com.vishalag53.pokedex.TypeConverter::class)
abstract class PokemonDatabase : RoomDatabase(){
    abstract fun pokemonDatabaseDao() : PokemonDatabaseDao
}