package com.vishalag53.pokedex.pokemon.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()


interface PokemonApiService{
    @GET("pokemon")
    fun getPokemonList(): Call<PokemonListProperty>

    @GET("pokemon/{name}")
    fun getPokemonInfo() : Call<PokemonInfo>
}

object PokemonApi{
    val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}