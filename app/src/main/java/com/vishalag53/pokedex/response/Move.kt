package com.vishalag53.pokedex.response

data class Move(
    val move: com.vishalag53.pokedex.response.MoveX,
    val version_group_details: List<com.vishalag53.pokedex.response.VersionGroupDetail>
)