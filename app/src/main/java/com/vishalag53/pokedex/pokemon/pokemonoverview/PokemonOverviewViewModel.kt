package com.vishalag53.pokedex.pokemon.pokemonoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishalag53.pokedex.pokemon.network.PokemonApi
import com.vishalag53.pokedex.pokemon.network.PokemonInfo
import com.vishalag53.pokedex.pokemon.network.PokemonListProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KProperty

class PokemonOverviewViewModel : ViewModel(){

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getPokemonProperties()
    }

    private fun getPokemonProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = PokemonApi.retrofitService.getPokemonList()
            try {
                val result = getPropertiesDeferred.await()
                _response.value = "Success:   Pokemon properties retrieved"
            } catch (e: Exception){
                _response.value = "Failure: ${e.message} "
            }

        }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
