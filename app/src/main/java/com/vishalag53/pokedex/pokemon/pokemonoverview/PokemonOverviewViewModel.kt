package com.vishalag53.pokedex.pokemon.pokemonoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishalag53.pokedex.pokemon.network.PokemonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonOverviewViewModel : ViewModel(){

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getPokemonProperties()
    }

    private fun getPokemonProperties() {
        PokemonApi.retrofitService.getProperties().enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

        })
    }

}