package com.vishalag53.pokedex.ability.abilitydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.database.abilityDatabase.AbilityEntity

@Suppress("UNCHECKED_CAST")
class AbilityDetailViewModelFactory(
    private val abilityEntity: AbilityEntity
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AbilityDetailViewModel::class.java)) {
            return AbilityDetailViewModel(abilityEntity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}