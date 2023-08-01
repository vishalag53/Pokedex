package com.vishalag53.pokedex.ability.abilitydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishalag53.pokedex.database.abilityDatabase.AbilityEntity

class AbilityDetailViewModel(
    abilityEntity: AbilityEntity
) : ViewModel(){

    private val _selectedProperty = MutableLiveData<AbilityEntity>()
    val selectedProperty: LiveData<AbilityEntity>
        get() = _selectedProperty

    init {
        _selectedProperty.value = abilityEntity
    }

}