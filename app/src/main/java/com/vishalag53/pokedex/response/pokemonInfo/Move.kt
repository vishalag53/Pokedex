package com.vishalag53.pokedex.response.pokemonInfo

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)