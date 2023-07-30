package com.vishalag53.pokedex.ability.abilitydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.database.abilityDatabase.AbilityEntity
import com.vishalag53.pokedex.repository.AbilityRepository

@Suppress("UNCHECKED_CAST")
class AbilityDetailViewModelFactory(
    private val abilityEntity: AbilityEntity,
    private val abilityRepository: AbilityRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AbilityDetailViewModel::class.java)) {
            return AbilityDetailViewModel(abilityEntity,abilityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}