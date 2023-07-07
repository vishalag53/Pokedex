package com.vishalag53.pokedex.pokemon.pokemonoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishalag53.pokedex.pokemon.network.PokemonApi
import com.vishalag53.pokedex.pokemon.network.PokemonListProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KProperty

class PokemonOverviewViewModel : ViewModel(){

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getPokemonProperties()
    }

    private fun getPokemonProperties() {
        PokemonApi.retrofitService.getPokemonList().enqueue(object: Callback<PokemonListProperty>{
            override fun onResponse(call: Call<PokemonListProperty>, response: Response<PokemonListProperty>) {
                    _response.value = "Success:  Pokemon properties retrieved"

            }

            override fun onFailure(call: Call<PokemonListProperty>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

        })



    }

}
