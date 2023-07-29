package com.vishalag53.pokedex.database.pokemonDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDatabaseDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonListView: PokemonEntity)

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemon(pokemonListView: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon_list_view")
    suspend fun getAllPokemonDetailListViews(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon_list_view WHERE name = :name")
    suspend fun getOnePokemonDetailListView(name : String): PokemonEntity?

    @Query("DELETE FROM pokemon_list_view")
    suspend fun deleteAllPokemon()

    @Query("UPDATE pokemon_list_view SET isFav = :fav WHERE name = :name")
    suspend fun updatePokemonFav(name: String,fav : Boolean)

}