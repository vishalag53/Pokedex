package com.vishalag53.pokedex.response

data class AbilityInfo(
    val effect_changes: List<EffectChange>,
    val effect_entries: List<EffectEntryXX>,
    val flavor_text_entries: List<FlavorTextEntryXX>,
    val generation: GenerationXX,
    val id: Int,
    val is_main_series: Boolean,
    val name: String,
    val names: List<NameXX>,
    val pokemon: List<PokemonX>
)