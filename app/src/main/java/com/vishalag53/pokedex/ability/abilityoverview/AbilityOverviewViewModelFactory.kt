package com.vishalag53.pokedex.ability.abilityoverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.repository.AbilityRepository

@Suppress("UNCHECKED_CAST")
class AbilityOverviewViewModelFactory(
    private val abilityRepository: AbilityRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AbilityOverviewViewModel(abilityRepository) as T
    }
}