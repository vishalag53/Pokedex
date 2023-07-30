package com.vishalag53.pokedex.ability.abilitydetail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.core.app.ShareCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.MyApplication
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentAbilityDetailBinding
import com.vishalag53.pokedex.network.PokemonApi
import com.vishalag53.pokedex.network.PokemonApiUtilities
import com.vishalag53.pokedex.repository.AbilityRepository

@Suppress("DEPRECATION")
class AbilityDetailFragment : Fragment() {

    private lateinit var binding: FragmentAbilityDetailBinding
    private lateinit var viewModel: AbilityDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(this.activity).application as MyApplication

        binding = FragmentAbilityDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val pokemonApi = PokemonApiUtilities.getInstance().create(PokemonApi::class.java)
        val abilityRepository = AbilityRepository(pokemonApi, application.daoDatabaseAbility)

        val abilityEntity = AbilityDetailFragmentArgs.fromBundle(requireArguments()).selectedPropertyAbility

        ViewModelProvider(
            this,
            AbilityDetailViewModelFactory(abilityEntity,abilityRepository)
        )[AbilityDetailViewModel::class.java].also { viewModel = it }

        binding.viewModel = viewModel


        setHasOptionsMenu(true)

        return binding.root
    }

    private fun getShareIntent(): Intent {
        val args = AbilityDetailFragmentArgs.fromBundle(requireArguments())

        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.share_favourite_ability,args.selectedPropertyAbility.name))
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
        when(item.itemId){
            R.id.shareMenu -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

}