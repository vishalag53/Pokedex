package com.vishalag53.pokedex.ability.abilityoverview

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.database.abilityDatabase.AbilityEntity
import com.vishalag53.pokedex.repository.AbilityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AbilityOverviewViewModel(private val abilityRepository: AbilityRepository): ViewModel() {

    val allAbilityListViews: LiveData<List<AbilityEntity>> = liveData {
        val abilityListViews = abilityRepository.getAllAbilityListFromDB()
        emit(abilityListViews)
    }

    init {
        viewModelScope.launch (Dispatchers.IO){
            //pokemonRepository.getAbilityListView()
        }
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