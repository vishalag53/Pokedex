package com.vishalag53.pokedex.network

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonApiUtilities {

    val BASE_URL = "https://pokeapi.co/api/v2/"

    fun getInstance(): Retrofit{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}