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
        viewModelScope.launch{
            //abilityRepository.getAbilityListView()
            //abilityRepository.deleteAllAbility()
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

    private val _noAbilityVisible = MutableLiveData<Int>()
    val noAbilityVisible: LiveData<Int>
        get() = _noAbilityVisible

    fun setVisibility(visibility: Int) {
        _noAbilityVisible.value = visibility
    }

    fun getAllAbilities(): List<AbilityEntity>{
        var entities = mutableListOf<AbilityEntity>()
        viewModelScope.launch {
            entities.addAll(abilityRepository.getAllAbilityListFromDB())
        }
        return entities
    }



}