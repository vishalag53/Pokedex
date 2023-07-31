package com.vishalag53.pokedex.response.abilityInfo

data class EffectChange(
    val effect_entries: List<EffectEntry>,
    val version_group: VersionGroup
)