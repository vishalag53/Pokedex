package com.vishalag53.pokedex.pokedex.pokedexdetail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.MyApplication
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentPokedexDetailBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.repository.FavoriteRepository
import com.vishalag53.pokedex.repository.PokedexRepository

@Suppress("DEPRECATION")
class PokedexDetailFragment : Fragment() {
    private lateinit var viewModel: PokedexDetailViewModel

    lateinit var binding: FragmentPokedexDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application as MyApplication

        binding = FragmentPokedexDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this


        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)

        val pokedexRepository = PokedexRepository(pokemonApi, application.daoDatabasePokedex)
        val favoriteRepository = FavoriteRepository(pokemonApi, application.daoDatabaseFavorite)

        val pokemonEntity =
            PokedexDetailFragmentArgs.fromBundle(requireArguments()).selectedPropertyPokemon

        ViewModelProvider(
            this,
            PokedexDetailViewModelFactory(pokemonEntity, pokedexRepository, favoriteRepository)
        )[PokedexDetailViewModel::class.java].also { viewModel = it }

        binding.viewModel = viewModel

        setActionBarContains(pokemonEntity.id,pokemonEntity.name.uppercase())

        // add or remove from favorite

        viewModel.getFavoriteStatus(pokemonEntity.name)

        binding.addFavoritePokemon.setOnClickListener{
            if(viewModel.isFav.value == true){
                viewModel.setFavouriteStatus(false,pokemonEntity)
            }
            else{
                viewModel.setFavouriteStatus(true,pokemonEntity)
            }
        }

        viewModel.isFav.observe(viewLifecycleOwner, Observer { isFav ->
            if(isFav){
                binding.addFavoritePokemon.setBackgroundResource(R.drawable.baseline_favorite_red_24)
            }
            else{
                binding.addFavoritePokemon.setBackgroundResource(R.drawable.baseline_favorite_border_24)
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun getShareIntent(): Intent{
        val args = PokedexDetailFragmentArgs.fromBundle(requireArguments())

        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.share_favourite_pokemon, args.selectedPropertyPokemon.name))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    @SuppressLint("QueryPermissionsNeeded")
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu,menu)
        if(null == getShareIntent().resolveActivity(requireActivity().packageManager)){
            menu.findItem(R.id.shareMenu)?.isVisible = false
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.shareMenu -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBarContains(id: String,name: String) {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.setPokemonName,id,name)
    }
}