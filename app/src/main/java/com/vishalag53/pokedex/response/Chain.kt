package com.vishalag53.pokedex.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chain(
    val evolution_details: List<Any>,
    @SerialName("evolves_to") val evolvesTo: List<EvolvesTo>,
    @SerialName("is_baby") val isBaby: Boolean,
    @SerialName("species") val species: SpeciesXXX
)