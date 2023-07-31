package com.vishalag53.pokedex.pokedex.pokedexoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishalag53.pokedex.MyApplication
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.database.pokedexDatabase.PokedexEntity
import com.vishalag53.pokedex.databinding.FragmentPokedexOverviewBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.repository.PokedexRepository


@Suppress( "DEPRECATION")
class PokedexOverviewFragment : Fragment() {

    private lateinit var viewModel: PokedexOverviewViewModel
    private lateinit var adapters: PokedexAdapters
    private lateinit var pokemonListEntities: List<PokedexEntity>
    private lateinit var searchView: SearchView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var binding: FragmentPokedexOverviewBinding
    private lateinit var pokedexRepository: PokedexRepository
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentPokedexOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application as MyApplication

        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)

        pokedexRepository = PokedexRepository(pokemonApi, application.daoDatabasePokedex)

        viewModel = ViewModelProvider(
            this,
            PokedexOverviewViewModelFactory(pokedexRepository)
        )[PokedexOverviewViewModel::class.java]

        adapters = PokedexAdapters(PokedexAdapters.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        binding.pokemonGrid.adapter = adapters

        viewModel.allPokemonListViews.observe(viewLifecycleOwner) {
            pokemonListEntities = it
            adapters.submitList(it)
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    PokedexOverviewFragmentDirections.actionPokemonOverviewFragmentToPokemonDetailFragment(
                        it
                    )
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        recyclerView = binding.pokemonGrid

        // Search View

        searchView = binding.pokemonSearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterPokemonList(query, query, query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterPokemonList(newText, newText, newText)
                return true
            }

        })

        // Layout Change

        val recyclerView: RecyclerView = binding.pokemonGrid
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        viewModel.currentLayoutType.observe(viewLifecycleOwner) { layoutType ->
            adapters.currentLayoutType = layoutType
            layoutManager = if (layoutType == PokedexAdapters.LayoutType.GRID) {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context)
            }
            binding.pokemonGrid.layoutManager = layoutManager
        }


        setHasOptionsMenu(true)

        return binding.root
    }

    private fun filterPokemonList(idQuery: String?, nameQuery: String?, typeQuery: String?) {
        val filteredList = pokemonListEntities.filter { pokemonEntity ->
            val matchesId =
                idQuery.isNullOrBlank() || pokemonEntity.id.contains(idQuery, ignoreCase = true)
            val matchesName = nameQuery.isNullOrBlank() || pokemonEntity.name.contains(
                nameQuery,
                ignoreCase = true
            )
            val matchesType1 = typeQuery.isNullOrBlank() || pokemonEntity.type1?.contains(
                typeQuery,
                ignoreCase = true
            ) == true
            val matchesType2 = typeQuery.isNullOrBlank() || pokemonEntity.type2?.contains(
                typeQuery,
                ignoreCase = true
            ) == true
            matchesId || matchesName || matchesType1 || matchesType2
        }

        val noPokemonFoundText = binding.noPokemonFoundText
        noPokemonFoundText.visibility = if(filteredList.isEmpty()) View.VISIBLE else View.GONE
        adapters.submitList(filteredList)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_change_menu, menu)
        layoutChangeInCOM(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.layoutChangeMenu -> workOnLayoutChange(item)
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Layout Change
    private fun layoutChangeInCOM(menu: Menu) {
        val toggleBtn = menu.findItem(R.id.layoutChangeMenu)
        toggleBtn.setIcon(
            if (adapters.currentLayoutType == PokedexAdapters.LayoutType.GRID) {
                R.drawable.view_list_48px
            } else {
                R.drawable.grid_on_48px
            }
        )
        toggleBtn.setTitle(
            if (adapters.currentLayoutType == PokedexAdapters.LayoutType.GRID) {
                R.string.change_in_list
            } else {
                R.string.change_in_grid
            }
        )
    }

    private fun workOnLayoutChange(item: MenuItem): Boolean {
        val viewModel: PokedexOverviewViewModel by viewModels()
        val currentLayoutType =
            if (viewModel.currentLayoutType.value == PokedexAdapters.LayoutType.GRID) {
                PokedexAdapters.LayoutType.LINEAR
            } else {
                PokedexAdapters.LayoutType.GRID
            }
        viewModel.setCurrentLayoutType(currentLayoutType)

        item.setIcon(
            if (adapters.currentLayoutType == PokedexAdapters.LayoutType.GRID) {
                R.drawable.view_list_48px
            } else {
                R.drawable.grid_on_48px
            }
        )
        item.setTitle(
            if (adapters.currentLayoutType == PokedexAdapters.LayoutType.GRID) {
                R.string.change_in_list
            } else {
                R.string.change_in_grid
            }
        )
        return true
    }


}