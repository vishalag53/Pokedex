package com.vishalag53.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.vishalag53.pokedex.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)
        val view = binding.root

        anim()

        binding.pokedexImgBtn.setOnClickListener{
            findNavController().navigate(R.id.navigate_homeSplashScreenFragment_to_pokedexFragment)
        }

        binding.abilityImgBtn.setOnClickListener{
            findNavController().navigate(R.id.navigate_homeSplashScreenFragment_to_abilityFragment)
        }

        binding.generationImgBtn.setOnClickListener{
            findNavController().navigate(R.id.navigate_homeSplashScreenFragment_to_generationFragment)
        }

        binding.moveImgBtn.setOnClickListener{
            findNavController().navigate(R.id.navigate_homeSplashScreenFragment_to_moveFragment)
        }

        binding.pokemonSpeciesImgBtn.setOnClickListener{
            findNavController().navigate(R.id.navigate_homeSplashScreenFragment_to_pokemonSpeciesFragment)
        }

        binding.regionImgBtn.setOnClickListener{
            findNavController().navigate(R.id.navigate_homeSplashScreenFragment_to_regionsFragment)
        }

        binding.statImgBtn.setOnClickListener{
            findNavController().navigate(R.id.navigate_homeSplashScreenFragment_to_statFragment)
        }

        binding.typeImgBtn.setOnClickListener{
            findNavController().navigate(R.id.navigate_homeSplashScreenFragment_to_typeFragment)
        }

        binding.favoriteImgBtn.setOnClickListener{
            findNavController().navigate(R.id.navigate_homeSplashScreenFragment_to_favoriteFragment)
        }

        return view
    }

    private fun anim() {
        val animLeft = AnimationUtils.loadAnimation(binding.root.context,R.anim.from_left)
        val animRight = AnimationUtils.loadAnimation(binding.root.context,R.anim.from_right)


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