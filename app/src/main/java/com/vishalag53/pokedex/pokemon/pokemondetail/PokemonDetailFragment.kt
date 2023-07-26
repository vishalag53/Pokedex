package com.vishalag53.pokedex.pokemon.pokemondetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.MyApplication
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentPokemonDetailBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.network.PokemonRepository

@Suppress("DEPRECATION")
class PokemonDetailFragment : Fragment() {
    private lateinit var viewModel: PokemonDetailViewModel

    lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application as MyApplication

        binding = FragmentPokemonDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this


        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)

        val pokemonRepository = PokemonRepository(pokemonApi, application.daoDatabasePokemon)

        val pokemonEntity =
            PokemonDetailFragmentArgs.fromBundle(requireArguments()).selectedProperty

        ViewModelProvider(
            this,
            PokemonDetailViewModelFactory(pokemonEntity, pokemonRepository)
        )[PokemonDetailViewModel::class.java].also { viewModel = it }

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun getShareIntent(): Intent{
        val args = PokemonDetailFragmentArgs.fromBundle(requireArguments())

        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.share_favourite_pokemon, args.selectedProperty.name))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu,menu)
        if(null == getShareIntent().resolveActivity(requireActivity().packageManager)){
            menu.findItem(R.id.shareMenu)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.shareMenu -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}