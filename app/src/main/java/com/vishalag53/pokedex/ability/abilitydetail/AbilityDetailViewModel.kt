package com.vishalag53.pokedex.ability.abilitydetail

import android.annotation.SuppressLint
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