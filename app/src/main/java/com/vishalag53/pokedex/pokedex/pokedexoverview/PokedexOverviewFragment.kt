package com.vishalag53.pokedex.pokedex.pokedexoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentPokedexOverviewBinding


class PokedexOverviewFragment : Fragment() {

    lateinit var binding: FragmentPokedexOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokedex_overview,container,false)


        return binding.root
    }

}