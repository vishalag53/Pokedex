package com.vishalag53.pokedex

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.vishalag53.pokedex.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        val view = binding.root



        anim()

        binding.PokemonImgBtn.setOnClickListener { 
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToPokemonOverviewFragment())
        }

        binding.pokedexImgBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToPokedexOverviewFragment())
        }

        binding.abilityImgBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToAbilityOverviewFragment())
        }

        binding.generationImgBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToGenerationOverviewFragment())
        }

        binding.moveImgBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToMoveOverviewFragment())
        }

        binding.pokemonSpeciesImgBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToPokemonSpeciesOverviewFragment())
        }

        binding.regionImgBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToRegionsOverviewFragment())
        }

        binding.statImgBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToStatOverviewFragment())
        }

        binding.typeImgBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToTypeOverviewFragment())
        }

        binding.favoriteImgBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToFavoriteOverviewFragment())
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.splashScreenFragment, false)
            goToHomeScreenOfMobile()
        }

        return view
    }

    private fun goToHomeScreenOfMobile() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun anim() {
        val animLeft = AnimationUtils.loadAnimation(binding.root.context, R.anim.from_left)
        val animRight = AnimationUtils.loadAnimation(binding.root.context, R.anim.from_right)


        binding.generationImgBtn.animation = animLeft
        binding.pokedexImgBtn.animation = animLeft
        binding.regionImgBtn.animation = animLeft
        binding.typeImgBtn.animation = animLeft

        binding.abilityImgBtn.animation = animRight
        binding.moveImgBtn.animation = animRight
        binding.pokemonSpeciesImgBtn.animation = animRight
        binding.statImgBtn.animation = animRight
        binding.favoriteImgBtn.animation = animRight
    }

}