package com.vishalag53.pokedex.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentPokedexBinding
import com.vishalag53.pokedex.databinding.FragmentPokemonBinding

class PokemonFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPokemonBinding>(inflater,R.layout.fragment_pokemon,container,false)

        val manager = GridLayoutManager(activity,2)
        binding.pokemonList.layoutManager = manager

        return binding.root
    }


}