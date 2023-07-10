package com.vishalag53.pokedex.pokemon.network

import com.vishalag53.pokedex.response.PokemonInfo
import com.vishalag53.pokedex.response.PokemonList
import retrofit2.Response
import retrofit2.http.GET


interface PokemonApi {

    @GET("pokemon/?offset=0&limit=1281")
    suspend fun getPokemonList(): Response<PokemonList>

    @GET("pokemon/greninja")
    suspend fun getPokemonInfo(): Response<PokemonInfo>

}