package com.vishalag53.pokedex.database.abilityDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vishalag53.pokedex.util.TypeConverter

@Database(entities = [AbilityEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class AbilityDatabase: RoomDatabase() {
    abstract fun abilityDatabaseDao(): AbilityDatabaseDao
}