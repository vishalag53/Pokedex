package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.databinding.FragmentPokemonOverviewBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.network.PokemonRepository

class PokemonOverviewFragment : Fragment() {

    private lateinit var viewModel: PokemonOverviewViewModel
    private lateinit var adapters: PokemonAdapters
    private var pokemonViewList = mutableListOf<PokemonListView>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = FragmentPokemonOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)

        val pokemonRepository = PokemonRepository(pokemonApi)

        viewModel = ViewModelProvider(
            this,
            PokemonOverviewViewModelFactory(pokemonRepository)
        )[PokemonOverviewViewModel::class.java]

        adapters = PokemonAdapters(this)
        binding.pokemonGrid.adapter = adapters


        viewModel.pokemonListView.observe(viewLifecycleOwner, Observer { pokemonListViewList ->
            this.pokemonViewList.clear()
            this.pokemonViewList.addAll(pokemonListViewList)
            adapters.submitList(this.pokemonViewList)
        })

        return binding.root
    }
}