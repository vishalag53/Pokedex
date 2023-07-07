package com.vishalag53.pokedex.pokemon.pokemondetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentPokemonDetailBinding


class PokemonDetailFragment : Fragment() {

    lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application

        //binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pokemon_detail,container,false)

        binding = FragmentPokemonDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

}