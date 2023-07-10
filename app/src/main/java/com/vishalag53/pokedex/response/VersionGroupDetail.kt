package com.vishalag53.pokedex.response

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.vishalag53.pokedex.response.MoveLearnMethod,
    val version_group: VersionGroup
)