package com.vishalag53.pokedex.network

import com.vishalag53.pokedex.response.ability.AbilityList
import com.vishalag53.pokedex.response.abilityInfo.AbilityInfo
import com.vishalag53.pokedex.response.pokemon.PokemonList
import com.vishalag53.pokedex.response.pokemonInfo.PokemonInfo
import com.vishalag53.pokedex.response.pokemonSpecies.PokemonSpeciesInfo
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

    @GET("ability?offset=0&limit=358")
    suspend fun getAbilityList() : Response<AbilityList>

    @GET("ability/{name}")
    suspend fun getAbilityInfo(
        @Path("name") name: String
    ): Response<AbilityInfo>

}