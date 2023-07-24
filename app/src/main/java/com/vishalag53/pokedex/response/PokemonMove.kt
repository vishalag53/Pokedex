package com.vishalag53.pokedex.response

data class PokemonMove(
    val accuracy: Int,
    val contest_combos: ContestCombos,
    val contest_effect: ContestEffect,
    val contest_type: ContestType,
    val damage_class: DamageClass,
    val effect_chance: Any,
    val effect_changes: List<Any>,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorTextEntryX>,
    val generation: GenerationX,
    val id: Int,
    val learned_by_pokemon: List<LearnedByPokemon>,
    val machines: List<Any>,
    val meta: Meta,
    val name: String,
    val names: List<NameX>,
    val past_values: List<Any>,
    val power: Int,
    val pp: Int,
    val priority: Int,
    val stat_changes: List<Any>,
    val super_contest_effect: SuperContestEffect,
    val target: Target,
    val type: TypeXX
)