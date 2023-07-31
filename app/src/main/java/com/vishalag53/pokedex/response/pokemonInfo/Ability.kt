package com.vishalag53.pokedex.response.pokemonInfo

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)