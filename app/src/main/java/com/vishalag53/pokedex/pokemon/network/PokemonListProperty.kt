package com.vishalag53.pokedex.pokemon.network

import com.squareup.moshi.Json

data class PokemonListProperty (
    @Json(name = "count")   val count: Int,
    @Json(name = "next")    val next: String?,
    @Json(name = "previous")   val previous: String?,
    @Json(name = "results") val results: List<Result>
) {
    data class Result(
        @Json(name = "name") val name: String,
        @Json(name = "url") val url: String
    )
}