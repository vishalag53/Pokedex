package com.vishalag53.pokedex.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentPokedexBinding


class PokedexFragment : Fragment() {

    lateinit var binding: FragmentPokedexBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokedex,container,false)

        binding.linearLayout.setOnClickListener{
            findNavController().navigate(PokedexFragmentDirections.navigatePokedexFragmentToPokemonDetailFragment())
        }

        return binding.root
    }

}