package com.vishalag53.pokedex.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishalag53.pokedex.MyApplication
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentFavoriteOverviewBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.repository.FavoriteRepository
import com.vishalag53.pokedex.repository.PokemonRepository


@Suppress("DEPRECATION")
class FavoriteOverviewFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteOverviewBinding
    private lateinit var viewModel: FavoriteOverviewViewModel
    private lateinit var adapters: FavoriteAdapters
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var favoriteRepository: FavoriteRepository
    private lateinit var pokemonRepository: PokemonRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_overview, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application as MyApplication
        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)
        favoriteRepository = FavoriteRepository(
            pokemonApi,
            application.daoDatabaseFavorite,
        )

        pokemonRepository = PokemonRepository(pokemonApi,application.daoDatabasePokemon)

        viewModel = ViewModelProvider(
            this,
            FavoriteOverviewViewModelFactory(pokemonRepository,favoriteRepository,application.daoDatabaseFavorite)
        )[FavoriteOverviewViewModel::class.java]

        adapters = FavoriteAdapters(FavoriteAdapters.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        binding.pokemonFavoriteGrid.adapter = adapters

        viewModel.allFavoritePokemonListViews.observe(viewLifecycleOwner) { favList ->
            if (favList.isEmpty()){
                binding.noFavoritePokemonPresent.visibility = View.VISIBLE
            }
            adapters.submitList(favList)
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    FavoriteOverviewFragmentDirections.actionFavoriteOverviewFragmentToPokemonDetailFragment(
                        it
                    )
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        // Layout Change

        val recyclerView: RecyclerView = binding.pokemonFavoriteGrid
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        viewModel.currentLayoutType.observe(viewLifecycleOwner) { layoutType ->
            adapters.currentLayoutType = layoutType
            layoutManager = if (layoutType == FavoriteAdapters.LayoutType.GRID) {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context)
            }
            binding.pokemonFavoriteGrid.layoutManager = layoutManager
        }

        viewModel.favoriteList.observe(viewLifecycleOwner, Observer { favList ->
            if (favList.isEmpty()){
                binding.noFavoritePokemonPresent.visibility = View.VISIBLE
            }
            adapters.submitList(favList)
        })

        viewModel.loadFavoriteFromDB()

        setHasOptionsMenu(true)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fav_fragment_menu, menu)
        val toggleBtn = menu.findItem(R.id.layoutChangeMenu)
        toggleBtn.setIcon(
            if (adapters.currentLayoutType == FavoriteAdapters.LayoutType.GRID) {
                R.drawable.view_list_48px
            } else {
                R.drawable.grid_on_48px
            }
        )
        toggleBtn.setTitle(
            if(adapters.currentLayoutType == FavoriteAdapters.LayoutType.GRID) {
                R.string.change_in_list
            } else {
                R.string.change_in_grid
            }
        )
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.layoutChangeMenu -> {
                val viewModel: FavoriteOverviewViewModel by viewModels()
                val currentLayoutType =
                    if (viewModel.currentLayoutType.value == FavoriteAdapters.LayoutType.GRID) {
                        FavoriteAdapters.LayoutType.LINEAR
                    } else {
                        FavoriteAdapters.LayoutType.GRID
                    }
                viewModel.setCurrentLayoutType(currentLayoutType)

                item.setIcon(
                    if (adapters.currentLayoutType == FavoriteAdapters.LayoutType.GRID) {
                        R.drawable.view_list_48px
                    } else {
                        R.drawable.grid_on_48px
                    }
                )
                item.setTitle(
                    if(adapters.currentLayoutType == FavoriteAdapters.LayoutType.GRID) {
                        R.string.change_in_list
                    } else {
                        R.string.change_in_grid
                    }
                )
                return true
            }
            R.id.deleteAllFav -> {
                viewModel.deleteAllFavorites()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}