package com.vishalag53.pokedex.pokemon.pokemonoverview.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonListViewEntity::class], version = 1, exportSchema = false)
abstract class PokemonListDatabase : RoomDatabase(){

    abstract fun pokemonListViewDao() : PokemonListDatabaseDao

}