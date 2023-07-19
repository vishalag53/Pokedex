package com.vishalag53.pokedex.pokemon.pokemonoverview.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonListDatabaseDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonListView: PokemonListViewEntity)

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonListView: List<PokemonListViewEntity>)

    @Query("SELECT * FROM pokemon_list_view")
    suspend fun getAllPokemonListViews(): List<PokemonListViewEntity>


}