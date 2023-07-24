package com.vishalag53.pokedex

import com.vishalag53.pokedex.response.EggGroup
import com.vishalag53.pokedex.response.PokemonInfo


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

fun getTotal(pokemonInfo: PokemonInfo): String? {
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

//fun getPokemonEvolutionChainId(evolutionChainUrl: String): String {
//    val idRegex = ".*/(\\d+)/$".toRegex()
//    val matchResult = idRegex.find(evolutionChainUrl)
//    return matchResult?.groupValues?.getOrNull(1) ?: ""
//}

//suspend fun getMoveName(moves: List<Move>, pokemonApi: PokemonApi): List<Move_Detail> {
//
//    val moveDetailList = mutableListOf<Move_Detail>()
//
//    for (i in moves.indices) {
//        val pokemonMoveResponse = pokemonApi.getPokemonMove(moves[i].move.name)
//        val pokemonMove = pokemonMoveResponse.body()
//        val moveDetail = Move_Detail(
//            moves[i].version_group_details[0].level_learned_at.toString(),
//            pokemonMove?.power.toString(),
//            pokemonMove?.accuracy.toString(),
//            pokemonMove?.damage_class.toString(),
//            pokemonMove?.type?.name.toString(),
//            pokemonMove?.pp.toString(),
//            pokemonMove?.accuracy.toString()
//        )
//        moveDetailList.add(moveDetail)
//    }
//    return moveDetailList
//}

//fun getEvolutionChainMinLevels(pokemonEvolutionTo: List<EvolvesTo>): List<String> {
//    val minLevels = mutableListOf<String>()
//
//    for (i in pokemonEvolutionTo.indices) {
//        for (j in pokemonEvolutionTo[i].evolution_details) {
//            val minLevel = j.minLevel.toString()
//            minLevels.add(minLevel)
//        }
//    }
//    return minLevels
//}

//fun getEvolutionChainSpeciesName(chain: Chain): List<String> {
//    val speciesList = mutableListOf<String>()
//
//    speciesList.add(chain.species.name)
//
//    for (i in chain.evolvesTo) {
//        speciesList.add(i.species.name)
//    }
//
//    return speciesList
//}