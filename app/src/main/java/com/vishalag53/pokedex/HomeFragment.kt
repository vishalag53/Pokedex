package com.vishalag53.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.vishalag53.pokedex.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)

        anim()

        return binding.root
    }

    private fun anim() {
        val animLeft = AnimationUtils.loadAnimation(binding.root.context,R.anim.from_left)
        val animRight = AnimationUtils.loadAnimation(binding.root.context,R.anim.from_right)

        binding.contentsTxtView.animation = animLeft
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