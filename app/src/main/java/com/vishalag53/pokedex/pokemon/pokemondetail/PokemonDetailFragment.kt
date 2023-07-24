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


        View.GONE.also {
            if (pokemonEntity.type1 == null) binding.txtType1.visibility = it
            if (pokemonEntity.type2 == null) binding.txtType2.visibility = it
            if (pokemonEntity.height == null) {
                binding.textViewHeight.visibility = it
                binding.txtHeight.visibility = it
            }
            if (pokemonEntity.weight == null) {
                binding.textViewHeight.visibility = it
                binding.txtWeight.visibility = it
            }
            if (pokemonEntity.front_default == null){
                binding.imgPokemon.setImageResource(R.drawable.pokeball)
                binding.icon1.visibility = it
            }
            if (pokemonEntity.front_shinny == null){
                binding.icon2.visibility = it
                binding.imgPokemon.setImageResource(R.drawable.pokeball)
            }

            if (pokemonEntity.egg_groups.isEmpty()) {
                binding.txtViewEggGroups.visibility = it
                binding.txtEggGroups.visibility = it
            }
            if (pokemonEntity.shape == null) binding.txtShape.visibility = it
            if (pokemonEntity.base_happiness == null) {
                binding.txtBaseHappiness.visibility = it
                binding.txtViewBaseHappiness.visibility = it
            }
            if (pokemonEntity.capture_rate == null) {
                binding.txtCaptureRate.visibility = it
                binding.txtViewCaptureRate.visibility = it
            }
            if (pokemonEntity.pokemon_color == null) {
                binding.txtViewColor.visibility = it
                binding.txtColor.visibility = it
            }

            if (pokemonEntity.growth_rates == null) {
                binding.txtViewGrowthRate.visibility = it
                binding.txtGrowthRates.visibility = it
            }
            if (pokemonEntity.hatch_count == null) {
                binding.txtViewHatchCounter.visibility = it
                binding.txtHatchCounter.visibility = it
            }
            if (pokemonEntity.shape == null) {
                binding.txtViewShape.visibility = it
                binding.txtViewShape.visibility = it
            }
            if (pokemonEntity.base_happiness == null && pokemonEntity.capture_rate == null && pokemonEntity.pokemon_color == null && pokemonEntity.egg_groups == null && pokemonEntity.growth_rates == null && pokemonEntity.hatch_count == null && pokemonEntity.shape == null) {
                binding.clAbout.visibility = it
            }
            if (pokemonEntity.hp == null) {
                binding.txtHP.visibility = it
                binding.txtViewHP.visibility = it
            }
            if (pokemonEntity.attack == null) {
                binding.txtAttack.visibility = it
                binding.txtViewAttack.visibility = it
            }
            if (pokemonEntity.defense == null) {
                binding.txDefense.visibility = it
                binding.txtViewDefense.visibility = it
            }
            if (pokemonEntity.special_attack == null) {
                binding.txtViewSpecialAttack.visibility = it
                binding.txtSpecialAttack.visibility = it
            }
            if (pokemonEntity.special_defense == null) {
                binding.txtSpecialDefense.visibility = it
                binding.txtViewSpecialDefense.visibility = it
            }
            if (pokemonEntity.speed == null) {
                binding.txtViewSpeed.visibility = it
                binding.txtSpeed.visibility = it
            }
            if (pokemonEntity.total == null) {
                binding.txtViewTotal.visibility = it
                binding.txtTotal.visibility = it
            }
        }

        if (pokemonEntity.height == null && pokemonEntity.weight == null) binding.txtNotPresentAbout.visibility =
            View.VISIBLE
        if (pokemonEntity.front_default == null && pokemonEntity.front_shinny == null) binding.txtNotPresentIcon.visibility =
            View.VISIBLE
        if (pokemonEntity.base_happiness == null && pokemonEntity.capture_rate == null && pokemonEntity.pokemon_color == null && pokemonEntity.egg_groups == null && pokemonEntity.growth_rates == null && pokemonEntity.hatch_count == null && pokemonEntity.shape == null) {
            binding.txtNotPresentAbout1.visibility = View.VISIBLE
        }
        if (pokemonEntity.hp == null && pokemonEntity.total == null && pokemonEntity.attack == null && pokemonEntity.defense == null && pokemonEntity.special_attack == null && pokemonEntity.special_defense == null && pokemonEntity.speed == null) {
            binding.txtNotPresent.visibility = View.VISIBLE
        }

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