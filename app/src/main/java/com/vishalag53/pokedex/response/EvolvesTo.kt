package com.vishalag53.pokedex.response

import kotlinx.serialization.Serializable

@Serializable
data class EvolvesTo(
    val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<EvolvesToX>,
    val is_baby: Boolean,
    val species: SpeciesXXX
)