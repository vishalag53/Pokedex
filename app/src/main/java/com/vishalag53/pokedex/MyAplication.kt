package com.vishalag53.pokedex

import android.app.Application
import androidx.room.Room
import com.vishalag53.pokedex.pokemon.pokemonoverview.database.PokemonListDatabase
import com.vishalag53.pokedex.pokemon.pokemonoverview.database.PokemonListDatabaseDao

class MyApplication: Application() {
    lateinit var databasePokemonOverView : PokemonListDatabase
    lateinit var pokemonListViewDao: PokemonListDatabaseDao

    override fun onCreate() {
        super.onCreate()

        databasePokemonOverView = Room.databaseBuilder(
            applicationContext,
            PokemonListDatabase::class.java,
            "pokemon_database"
        )
            .build()

        pokemonListViewDao = databasePokemonOverView.pokemonListViewDao()


    }
}