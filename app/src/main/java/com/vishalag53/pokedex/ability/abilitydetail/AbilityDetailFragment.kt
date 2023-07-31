package com.vishalag53.pokedex.ability.abilitydetail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentAbilityDetailBinding

@Suppress("DEPRECATION")
class AbilityDetailFragment : Fragment() {

    private lateinit var binding: FragmentAbilityDetailBinding
    private lateinit var viewModel: AbilityDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentAbilityDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this


        val abilityEntity = AbilityDetailFragmentArgs.fromBundle(requireArguments()).selectedPropertyAbility

        ViewModelProvider(
            this,
            AbilityDetailViewModelFactory(abilityEntity)
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

    @Deprecated("Deprecated in Java")
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu,menu)
        if(null == getShareIntent().resolveActivity(requireActivity().packageManager)){
            menu.findItem(R.id.shareMenu)?.isVisible = false
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.shareMenu -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

}