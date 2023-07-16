package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalag53.pokedex.network.PokemonRepository
import com.vishalag53.pokedex.response.PokemonInfo
import com.vishalag53.pokedex.response.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokemonOverviewViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name
    fun setName(data: String){
        _name.value = data
    }


    private val _view = MutableLiveData<List<PokemonView>>()
    val view : LiveData<List<PokemonView>>
        get() = _view

    init {
        viewModelScope.launch (Dispatchers.IO) {
            Log.d("VISHAL","launch")
            pokemonRepository.getPokemon()
        }
    }

     fun getInfo(){
//        pokemonRepository.name = _name.value ?: ""
        viewModelScope.launch (Dispatchers.IO) {
            Log.d("VISHAL","getInfo  ${name.value}")
            var data = _name.value ?: ""
            pokemonRepository.getPokemonInfo("greninja")
        }
    }


    val pokemon: LiveData<PokemonList>
        get() = pokemonRepository.pokemonLiveData

    val pokemonInfo: LiveData<PokemonInfo>
        get() = pokemonRepository.pokemonInfo


}