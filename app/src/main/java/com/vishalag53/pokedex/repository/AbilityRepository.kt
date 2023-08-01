package com.vishalag53.pokedex.repository

import com.vishalag53.pokedex.database.abilityDatabase.AbilityDatabaseDao
import com.vishalag53.pokedex.database.abilityDatabase.AbilityEntity
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.util.getEffectFromAbility
import com.vishalag53.pokedex.util.getInDepthEffectFromAbility
import com.vishalag53.pokedex.util.getPokemonNameListFromAbility

class AbilityRepository(
    private val pokemonApi: PokemonApi,
    private val abilityDatabaseDao: AbilityDatabaseDao
) {
    suspend fun getAbilityListView(){
        val abilityListResponse = pokemonApi.getAbilityList()
        val abilityList = abilityListResponse.body()?.results ?: emptyList()

        val abilityEntity = mutableListOf<AbilityEntity>()

        for (ability in abilityList) {

            val abilityInfoResponse = pokemonApi.getAbilityInfo(ability.name)
            val abilityInfo = abilityInfoResponse.body()

            if (abilityInfo != null){
                val abilityEntityList = AbilityEntity(
                    name = abilityInfo.name,
                    inDepthEffect = getInDepthEffectFromAbility(abilityInfo.effect_entries),
                    effect = getEffectFromAbility(abilityInfo.effect_entries),
                    pokemonNameList = getPokemonNameListFromAbility(abilityInfo.pokemon)
                )
                abilityEntity.add(abilityEntityList)
            }
        }

        abilityDatabaseDao.insertAllAbility(abilityEntity)
    }

    suspend fun getAllAbilityListFromDB(): List<AbilityEntity>{
        return abilityDatabaseDao.getAllAbilityDetailListViews()
    }

    suspend fun getOneAbilityDetail(name: String): AbilityEntity? {
        return abilityDatabaseDao.getOneAbilityDetailListView(name)
    }

    suspend fun deleteAllAbility(){
        abilityDatabaseDao.deleteAllAbility()
    }
}