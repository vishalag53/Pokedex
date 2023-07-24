package com.vishalag53.pokedex

import android.app.Application
import androidx.room.Room
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonDatabase
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonDatabaseDao

class MyApplication: Application() {


    lateinit var databasePokemon: PokemonDatabase
    lateinit var daoDatabasePokemon: PokemonDatabaseDao

    override fun onCreate() {
        super.onCreate()

        databasePokemon = Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon_detail_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        daoDatabasePokemon = databasePokemon.pokemonDatabaseDao()


    }
}