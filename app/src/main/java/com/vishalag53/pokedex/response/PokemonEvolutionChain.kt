package com.vishalag53.pokedex.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonEvolutionChain(
    val baby_trigger_item: Any,
    val chain: Chain,
    val id: Int
)