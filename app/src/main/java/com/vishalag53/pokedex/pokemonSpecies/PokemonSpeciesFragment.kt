package com.vishalag53.pokedex.pokemonSpecies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentPokemonSpeciesBinding


class PokemonSpeciesFragment : Fragment() {

    lateinit var binding: FragmentPokemonSpeciesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_species,container,false)
        val view = binding.root

        return view
    }

}