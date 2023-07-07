package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentPokemonOverviewBinding

class PokemonOverviewFragment : Fragment() {

    private val viewModel: PokemonOverviewViewModel by lazy {
        ViewModelProvider(this)[PokemonOverviewViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val binding = DataBindingUtil.inflate<FragmentPokemonOverviewBinding>(inflater,R.layout.fragment_pokemon_overview,container,false)

        val binding = FragmentPokemonOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //val manager = GridLayoutManager(activity,3)
        //binding.pokemonList.layoutManager = manager

        return binding.root
    }


}