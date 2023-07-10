package com.vishalag53.pokedex.response

data class PokemonInfo(
    val abilities: List<com.vishalag53.pokedex.response.Ability>,
    val base_experience: Int,
    val forms: List<com.vishalag53.pokedex.response.Form>,
    val game_indices: List<com.vishalag53.pokedex.response.GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<com.vishalag53.pokedex.response.Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: com.vishalag53.pokedex.response.Species,
    val sprites: com.vishalag53.pokedex.response.Sprites,
    val stats: List<com.vishalag53.pokedex.response.Stat>,
    val types: List<com.vishalag53.pokedex.response.Type>,
    val weight: Int
)