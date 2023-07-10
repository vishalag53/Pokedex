package com.vishalag53.pokedex.response

data class Ability(
    val ability: com.vishalag53.pokedex.response.AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)