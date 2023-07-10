package com.vishalag53.pokedex.response

data class PokemonList(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)