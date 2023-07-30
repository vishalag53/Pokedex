package com.vishalag53.pokedex.ability.abilitydetail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishalag53.pokedex.database.abilityDatabase.AbilityEntity
import com.vishalag53.pokedex.repository.AbilityRepository
import com.vishalag53.pokedex.repository.PokemonRepository

class AbilityDetailViewModel(
    private val abilityEntity: AbilityEntity,
    private val abilityRepository: AbilityRepository
) : ViewModel(){

    private val _selectedProperty = MutableLiveData<AbilityEntity>()
    val selectedProperty: LiveData<AbilityEntity>
        get() = _selectedProperty

    init {
        _selectedProperty.value = abilityEntity
    }

    private val _navigateToSelectedProperty = MutableLiveData<AbilityEntity>()
    val navigateToSelectedProperty: LiveData<AbilityEntity>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(abilityEntity: AbilityEntity){
        _navigateToSelectedProperty.value = abilityEntity
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }

}