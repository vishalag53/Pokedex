package com.vishalag53.pokedex.pokemonSpecies.pokemonspeciesoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentPokemonSpeciesOverviewBinding


class PokemonSpeciesOverviewFragment : Fragment() {

    lateinit var binding: FragmentPokemonSpeciesOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_species_overview,container,false)
        val view = binding.root

        return view
    }

}