package com.vishalag53.pokedex.response

data class EvolvesToX(
    val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<Any>,
    val is_baby: Boolean,
    val species: SpeciesXXX
)