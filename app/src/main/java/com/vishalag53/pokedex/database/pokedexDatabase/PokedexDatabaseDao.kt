package com.vishalag53.pokedex.database.pokedexDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokedexDatabaseDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonListView: PokedexEntity)

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemon(pokemonListView: List<PokedexEntity>)

    @Query("SELECT * FROM pokedex_list_view")
    suspend fun getAllPokemonDetailListViews(): List<PokedexEntity>

    @Query("SELECT * FROM pokedex_list_view WHERE name = :name")
    suspend fun getOnePokemonDetailListView(name : String): PokedexEntity?

    @Query("DELETE FROM pokedex_list_view")
    suspend fun deleteAllPokemon()

    @Query("UPDATE pokedex_list_view SET isFav = :fav WHERE name = :name")
    suspend fun updatePokemonFav(name: String,fav : Boolean)

}