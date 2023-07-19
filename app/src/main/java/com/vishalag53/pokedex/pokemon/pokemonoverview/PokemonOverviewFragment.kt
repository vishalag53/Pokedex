package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.MyApplication
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

        val application = requireNotNull(this.activity).application as MyApplication

        val dataSource = application.databasePokemonOverView

        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)


        val pokemonRepository = PokemonRepository(pokemonApi,dataSource.pokemonListViewDao())


        viewModel = ViewModelProvider(
            this,
            PokemonOverviewViewModelFactory(pokemonRepository)
        )[PokemonOverviewViewModel::class.java]

        adapters = PokemonAdapters(this)
        binding.pokemonGrid.adapter = adapters

        viewModel.allPokemonListViews.observe(viewLifecycleOwner){ pokemonListViews->

            val pokemonListViewList = pokemonListViews.map { pokemonListViewEntity ->
                PokemonListView(
                    PokemonView(
                        pokemonListViewEntity.img,
                        pokemonListViewEntity.id,
                        pokemonListViewEntity.name,
                        pokemonListViewEntity.type1,
                        pokemonListViewEntity.type2
                    )
                )
            }

            this.pokemonViewList.clear()
            this.pokemonViewList.addAll(pokemonListViewList)
            adapters.submitList(this.pokemonViewList)
        }

        return binding.root
    }
}