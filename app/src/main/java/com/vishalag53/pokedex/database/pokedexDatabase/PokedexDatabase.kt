package com.vishalag53.pokedex.database.pokedexDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vishalag53.pokedex.util.TypeConverter


@Database(entities = [PokedexEntity::class], version = 8, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class PokedexDatabase : RoomDatabase(){
    abstract fun pokedexDatabaseDao() : PokedexDatabaseDao
}