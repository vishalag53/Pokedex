package com.vishalag53.pokedex.network

import com.vishalag53.pokedex.response.AbilityInfo
import com.vishalag53.pokedex.response.AbilityList
import com.vishalag53.pokedex.response.PokemonEvolutionChain
import com.vishalag53.pokedex.response.PokemonInfo
import com.vishalag53.pokedex.response.PokemonList
import com.vishalag53.pokedex.response.PokemonMove
import com.vishalag53.pokedex.response.PokemonSpeciesInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonApi {

    @GET("pokemon/?offset=0&limit=1010")
    suspend fun getPokemonList(): Response<PokemonList>

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Response<PokemonInfo>


    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpeciesInfo(
        @Path("id") id: Int
    ): Response<PokemonSpeciesInfo>


//    @GET("evolution-chain/{id}")
//    suspend fun getPokemonEvolutionChain(
//        @Path("id") id: String
//    ): Response<PokemonEvolutionChain>


    @GET("move/{name}")
    suspend fun getPokemonMove(
        @Path("name") name: String
    ): Response<PokemonMove>


    @GET("ability?offset=0&limit=358")
    suspend fun getAbilityList() : Response<AbilityList>

    @GET("ability/{name}")
    suspend fun getAbilityInfo(
        @Path("name") name: String
    ): Response<AbilityInfo>

}