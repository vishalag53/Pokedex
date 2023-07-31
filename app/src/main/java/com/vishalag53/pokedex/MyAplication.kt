package com.vishalag53.pokedex

import android.app.Application
import androidx.room.Room
import com.vishalag53.pokedex.database.abilityDatabase.AbilityDatabase
import com.vishalag53.pokedex.database.abilityDatabase.AbilityDatabaseDao
import com.vishalag53.pokedex.database.favoriteDatabase.FavoriteDatabase
import com.vishalag53.pokedex.database.favoriteDatabase.FavoriteDatabaseDao
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonDatabase
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonDatabaseDao

class MyApplication: Application() {


    lateinit var databasePokemon: PokemonDatabase
    lateinit var daoDatabasePokemon: PokemonDatabaseDao

    lateinit var databaseFavorite: FavoriteDatabase
    lateinit var daoDatabaseFavorite: FavoriteDatabaseDao

    lateinit var databaseAbility: AbilityDatabase
    lateinit var daoDatabaseAbility: AbilityDatabaseDao

    override fun onCreate() {
        super.onCreate()

        // Database Pokemon

        databasePokemon = Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon_detail_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        daoDatabasePokemon = databasePokemon.pokemonDatabaseDao()

        // Database Favorite

        databaseFavorite = Room.databaseBuilder(
            applicationContext,
            FavoriteDatabase::class.java,
            "favorite_list_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        daoDatabaseFavorite = databaseFavorite.favoriteDatabaseDao()

        // Database Ability

        databaseAbility = Room.databaseBuilder(
            applicationContext,
            AbilityDatabase::class.java,
            "ability_detail_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        daoDatabaseAbility = databaseAbility.abilityDatabaseDao()
    }
}