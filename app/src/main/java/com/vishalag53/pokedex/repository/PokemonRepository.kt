package com.vishalag53.pokedex.repository

import com.vishalag53.pokedex.database.pokemonDatabase.PokemonDatabaseDao
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.util.getId
import com.vishalag53.pokedex.util.getBkgColor
import com.vishalag53.pokedex.util.getEggGroups
import com.vishalag53.pokedex.util.getTotal


class PokemonRepository(
    private val pokemonApi: PokemonApi,
    private val pokemonDatabaseDao: PokemonDatabaseDao
) {

    suspend fun getPokemonListView() {
        val pokemonListResponse = pokemonApi.getPokemonList()
        val pokemonList = pokemonListResponse.body()?.results ?: emptyList()

        val pokemonEntity = mutableListOf<PokemonEntity>()

        for (pokemon in pokemonList) {

            val pokemonInfoResponse = pokemonApi.getPokemonInfo(pokemon.name)
            val pokemonInfo = pokemonInfoResponse.body()

            val pokemonId = pokemonInfo!!.id

            val pokemonSpeciesResponse = pokemonApi.getPokemonSpeciesInfo(pokemonId)
            val pokemonSpecies = pokemonSpeciesResponse.body()

            if (pokemonSpecies != null) {
                val pokemonEntityList = PokemonEntity(
                    front_default = pokemonInfo.sprites.front_default,
                    id = getId(pokemonInfo.id.toString()),
                    name = pokemonInfo.name,
                    type1 = pokemonInfo.types.getOrNull(0)?.type?.name,
                    type2 = pokemonInfo.types.getOrNull(1)?.type?.name,
                    height = pokemonInfo.height.toString(),
                    weight = pokemonInfo.weight.toString(),
                    hp = pokemonInfo.stats.getOrNull(0)?.base_stat.toString(),
                    attack = pokemonInfo.stats.getOrNull(1)?.base_stat.toString(),
                    defense = pokemonInfo.stats.getOrNull(2)?.base_stat.toString(),
                    special_attack = pokemonInfo.stats.getOrNull(3)?.base_stat.toString(),
                    special_defense = pokemonInfo.stats.getOrNull(4)?.base_stat.toString(),
                    speed = pokemonInfo.stats.getOrNull(5)?.base_stat.toString(),
                    color = getBkgColor(pokemonInfo.types.getOrNull(0)?.type?.name),
                    total = getTotal(pokemonInfo),
                    front_shinny = pokemonInfo.sprites.front_shiny,
                    base_happiness = pokemonSpecies.base_happiness.toString(),
                    capture_rate = pokemonSpecies.capture_rate.toString(),
                    pokemon_color = pokemonSpecies.color.name,
                    egg_groups = getEggGroups(pokemonSpecies.egg_groups),
                    growth_rates = pokemonSpecies.growth_rate.name,
                    hatch_count = pokemonSpecies.hatch_counter.toString(),
                    shape = pokemonSpecies.shape?.name,
                    isFav = false
                )
                pokemonEntity.add(pokemonEntityList)
            }
        }

        pokemonDatabaseDao.insertAllPokemon(pokemonEntity)
    }

    suspend fun getAllPokemonListViewFromDB(): List<PokemonEntity> {
        return pokemonDatabaseDao.getAllPokemonDetailListViews()
    }

    suspend fun getOnePokemonDetail(name: String): PokemonEntity? {
        return pokemonDatabaseDao.getOnePokemonDetailListView(name)
    }

    suspend fun deleteAllPokemon() {
        pokemonDatabaseDao.deleteAllPokemon()
    }

    suspend fun updatePokemonFav(name: String,isFav: Boolean){
        pokemonDatabaseDao.updatePokemonFav(name,isFav)
    }

}