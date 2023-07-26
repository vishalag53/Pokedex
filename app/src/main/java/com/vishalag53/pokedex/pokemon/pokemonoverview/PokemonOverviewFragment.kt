package com.vishalag53.pokedex.pokemon.pokemonoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vishalag53.pokedex.MyApplication
import com.vishalag53.pokedex.database.pokemonDatabase.PokemonEntity
import com.vishalag53.pokedex.databinding.FragmentPokemonOverviewBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.network.PokemonRepository


class PokemonOverviewFragment : Fragment() {

    private lateinit var viewModel: PokemonOverviewViewModel
    private lateinit var adapters: PokemonAdapters
    private lateinit var pokemonListEntities : List<PokemonEntity>
    private lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = FragmentPokemonOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application as MyApplication

        val dataSource = application.databasePokemon

        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)


        val pokemonRepository = PokemonRepository(pokemonApi,dataSource.pokemonDatabaseDao())


        viewModel = ViewModelProvider(
            this,
            PokemonOverviewViewModelFactory(pokemonRepository)
        )[PokemonOverviewViewModel::class.java]

        adapters = PokemonAdapters(PokemonAdapters.OnClickListener{
              viewModel.displayPropertyDetails(it)
        })
        binding.pokemonGrid.adapter = adapters

        viewModel.allPokemonListViews.observe(viewLifecycleOwner){
            pokemonListEntities = it
            adapters.submitList(it)
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it){
                this.findNavController().navigate(PokemonOverviewFragmentDirections.actionPokemonOverviewFragmentToPokemonDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        searchView = binding.pokemonSearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterPokemonList(query, query, query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterPokemonList(newText,newText,newText)
                return true
            }

        })


        return binding.root
    }

    private fun filterPokemonList(idQuery: String?,nameQuery : String?,typeQuery: String?){
        val filteredList = pokemonListEntities.filter { pokemonEntity ->
            val matchesId = idQuery.isNullOrBlank() || pokemonEntity.id.contains(idQuery,ignoreCase = true)
            val matchesName = nameQuery.isNullOrBlank() || pokemonEntity.name.contains(nameQuery,ignoreCase = true)
            val matchesType1 = typeQuery.isNullOrBlank() || pokemonEntity.type1?.contains(typeQuery,ignoreCase = true) == true
            val matchesType2 = typeQuery.isNullOrBlank() || pokemonEntity.type2?.contains(typeQuery,ignoreCase = true) == true
            matchesId || matchesName || matchesType1 || matchesType2
        }
        adapters.submitList(filteredList)
    }


}