package com.vishalag53.pokedex.pokemon.pokemonoverview.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_list_view")
data class PokemonListViewEntity (
    @PrimaryKey(autoGenerate = true)
    val id1: Int = 0,
    val img: String?,
    val id: String,
    val name: String,
    val type1: String?,
    val type2: String?
    )