package com.vishalag53.pokedex.response.ability

data class AbilityList(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)
