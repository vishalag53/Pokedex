package com.vishalag53.pokedex.database.pokemonDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDatabaseDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonListView: PokemonEntity)

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAllDetail(pokemonListView: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon_list_view")
    suspend fun getAllPokemonDetailListViews(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon_list_view WHERE name = :name")
    suspend fun getOnePokemonDetailListView(name : String): PokemonEntity?

    @Query("DELETE FROM pokemon_list_view")
    suspend fun deleteAll()
}