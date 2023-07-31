package com.vishalag53.pokedex.util

import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.response.abilityInfo.EffectEntryX
import com.vishalag53.pokedex.response.abilityInfo.Pokemon
import com.vishalag53.pokedex.response.pokemonInfo.PokemonInfo
import com.vishalag53.pokedex.response.pokemonSpecies.EggGroup


fun getId(id: String): String = when (id.length) {
    1 -> "#00${id}"
    2 -> "#0${id}"
    else -> "#${id}"
}

fun getBkgColor(name: String?): Int = when (name) {
    "fire" -> R.color.TypeFire
    "water" -> R.color.TypeWater
    "electric" -> R.color.TypeElectric
    "grass" -> R.color.TypeGrass
    "ice" -> R.color.TypeIce
    "fighting" -> R.color.TypeFighting
    "poison" -> R.color.TypePoison
    "ground" -> R.color.TypeGround
    "flying" -> R.color.TypeFlying
    "psychic" -> R.color.TypePsychic
    "bug" -> R.color.TypeBug
    "rock" -> R.color.TypeRock
    "ghost" -> R.color.TypeGhost
    "dragon" -> R.color.TypeDragon
    "dark" -> R.color.TypeDark
    "steel" -> R.color.TypeSteel
    "fairy" -> R.color.TypeFairy
    else -> R.color.TypeNormal
}

fun getTotal(pokemonInfo: PokemonInfo): String {
    var sum = 0
    for (i in 0 until 6) {
        sum += (pokemonInfo.stats.getOrNull(i)?.base_stat!!)
    }
    return sum.toString()
}

fun getEggGroups(eggGroups: List<EggGroup>) : List<String>{
    val eggGroupsList = mutableListOf<String>()

    for (eggGroup in eggGroups){
        eggGroupsList.add(eggGroup.name)
    }

    return eggGroupsList
}

fun getInDepthEffectFromAbility(effectEntries: List<EffectEntryX>): String{
    var inDepthEffect = ""

    for(effect in effectEntries){
        if(effect.language.name == "en"){
            inDepthEffect = effect.effect
        }
    }
    return inDepthEffect
}

fun getEffectFromAbility(effectEntries: List<EffectEntryX>): String{
    var effect = ""

    for (effect1 in effectEntries){
        if(effect1.language.name == "en"){
            effect = effect1.short_effect
        }
    }
    return effect
}

fun getPokemonNameListFromAbility(pokemonList: List<Pokemon>) : List<String>{
    val pokemonNameList = mutableListOf<String>()

    for(pokemon in pokemonList){
        val id: Int = getIdFromUrl(pokemon.pokemon.url)

        if(id in 1..1010){
            pokemonNameList.add(pokemon.pokemon.name)
        }
    }

    return pokemonNameList
}

fun getIdFromUrl(url: String): Int{
    val s =  url.split("/")
    val size = s.size
    return s[size-2].toInt()
}