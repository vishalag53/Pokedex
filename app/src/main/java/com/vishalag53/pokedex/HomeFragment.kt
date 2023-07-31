package com.vishalag53.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.vishalag53.pokedex.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        val view = binding.root



        anim()

        binding.pokemonCardView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToPokemonOverviewFragment())
        }

        binding.abilityCardView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToAbilityOverviewFragment())
        }

        binding.favoriteCardView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateHomeFragmentToFavoriteOverviewFragment())
        }


        return view
    }


    private fun anim() {
        val animLeft = AnimationUtils.loadAnimation(binding.root.context, R.anim.from_left)
        val animRight = AnimationUtils.loadAnimation(binding.root.context, R.anim.from_right)

        binding.pokemonCardView.animation = animLeft
        binding.abilityCardView.animation = animRight
        binding.favoriteCardView.animation = animLeft
    }

}