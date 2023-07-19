package com.vishalag53.pokedex.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vishalag53.pokedex.pokemon.pokemonoverview.PokemonView
import com.vishalag53.pokedex.pokemon.pokemonoverview.PokemonListView
import com.vishalag53.pokedex.pokemon.pokemonoverview.database.PokemonListDatabase
import com.vishalag53.pokedex.pokemon.pokemonoverview.database.PokemonListDatabaseDao
import com.vishalag53.pokedex.pokemon.pokemonoverview.database.PokemonListViewEntity
import com.vishalag53.pokedex.response.PokemonList


class PokemonRepository(
    private val pokemonApi: PokemonApi,
    private val pokemonListViewDatabaseDao: PokemonListDatabaseDao,
) {

    suspend fun getPokemonListView() {
        val pokemonListResponse = pokemonApi.getPokemonList()
        val pokemonList = pokemonListResponse.body()?.results ?: emptyList()

        val pokemonListViewList = mutableListOf<PokemonListView>()

        for (pokemon in pokemonList) {
            val pokemonInfoResponse = pokemonApi.getPokemonInfo(pokemon.name)
            val pokemonInfo = pokemonInfoResponse.body()
            if (pokemonInfo != null) {
                val pokemonView = PokemonView(
                    pokemonInfo.sprites.front_default,
                    pokemonInfo.id.toString(),
                    pokemonInfo.name,
                    pokemonInfo.types.getOrNull(0)?.type?.name,
                    pokemonInfo.types.getOrNull(1)?.type?.name
                )
                val pokemonListView = PokemonListView(pokemonView)
                pokemonListViewList.add(pokemonListView)
            }
        }

        val pokemonListViewEntities = pokemonListViewList.map { pokemonListView ->
            PokemonListViewEntity(
                img = pokemonListView.pokemonView.img,
                id = pokemonListView.pokemonView.id,
                name = pokemonListView.pokemonView.name,
                type1 = pokemonListView.pokemonView.type1,
                type2 = pokemonListView.pokemonView.type2,
            )
        }


        val existingData = pokemonListViewDatabaseDao.getAllPokemonListViews()
        val newData = pokemonListViewEntities.filterNot { existingData.contains(it) }
        pokemonListViewDatabaseDao.insertAll(newData)

    }

    suspend fun getAllPokemonListFromDB() : List<PokemonListViewEntity>{
        return pokemonListViewDatabaseDao.getAllPokemonListViews()
    }


    private val _pokemonLiveData = MutableLiveData<PokemonList>()

    val pokemonLiveData : LiveData<PokemonList>
    get() = _pokemonLiveData

}