package com.vishalag53.pokedex.repository

import com.vishalag53.pokedex.database.pokedexDatabase.PokedexDatabaseDao
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.util.getId
import com.vishalag53.pokedex.util.getBkgColor
import com.vishalag53.pokedex.util.getTotal


class PokedexRepository(
    private val pokemonApi: PokemonApi,
    private val pokedexDatabaseDao: PokedexDatabaseDao
) {

    suspend fun getPokemonListView() {
        val pokemonListResponse = pokemonApi.getPokemonList()
        val pokemonList = pokemonListResponse.body()?.results ?: emptyList()

        val pokedexEntity = mutableListOf<PokedexEntity>()

        for (pokemon in pokemonList) {

            val pokemonInfoResponse = pokemonApi.getPokemonInfo(pokemon.name)
            val pokemonInfo = pokemonInfoResponse.body()

            val pokemonId = pokemonInfo!!.id

            val pokemonSpeciesResponse = pokemonApi.getPokemonSpeciesInfo(pokemonId)
            val pokemonSpecies = pokemonSpeciesResponse.body()

            if (pokemonSpecies != null) {
                val pokedexEntityList = PokedexEntity(
                    frontDefault = pokemonInfo.sprites.front_default,
                    id = getId(pokemonInfo.id.toString()),
                    name = pokemonInfo.name,
                    type1 = pokemonInfo.types.getOrNull(0)?.type?.name,
                    type2 = pokemonInfo.types.getOrNull(1)?.type?.name,
                    height = pokemonInfo.height.toString(),
                    weight = pokemonInfo.weight.toString(),
                    hp = pokemonInfo.stats.getOrNull(0)?.base_stat.toString(),
                    attack = pokemonInfo.stats.getOrNull(1)?.base_stat.toString(),
                    defense = pokemonInfo.stats.getOrNull(2)?.base_stat.toString(),
                    specialAttack = pokemonInfo.stats.getOrNull(3)?.base_stat.toString(),
                    specialDefense = pokemonInfo.stats.getOrNull(4)?.base_stat.toString(),
                    speed = pokemonInfo.stats.getOrNull(5)?.base_stat.toString(),
                    color = getBkgColor(pokemonInfo.types.getOrNull(0)?.type?.name),
                    total = getTotal(pokemonInfo),
                    frontShinny = pokemonInfo.sprites.front_shiny,
                    baseHappiness = pokemonSpecies.base_happiness.toString(),
                    captureRate = pokemonSpecies.capture_rate.toString(),
                    pokemonColor = pokemonSpecies.color.name,
                    eggGroups = pokemonSpecies.egg_groups[0].name,
                    growthRates = pokemonSpecies.growth_rate.name,
                    hatchCount = pokemonSpecies.hatch_counter.toString(),
                    shape = pokemonSpecies.shape?.name,
                    isFav = false
                )
                pokedexEntity.add(pokedexEntityList)
            }
        }

        pokedexDatabaseDao.insertAllPokemon(pokedexEntity)
    }

    suspend fun getAllPokemonListViewFromDB(): List<PokedexEntity> {
        return pokedexDatabaseDao.getAllPokemonDetailListViews()
    }

    suspend fun getOnePokemonDetail(name: String): PokedexEntity? {
        return pokedexDatabaseDao.getOnePokemonDetailListView(name)
    }

    suspend fun deleteAllPokemon() {
        pokedexDatabaseDao.deleteAllPokemon()
    }

    suspend fun updatePokemonFav(name: String,isFav: Boolean){
        pokedexDatabaseDao.updatePokemonFav(name,isFav)
    }

}