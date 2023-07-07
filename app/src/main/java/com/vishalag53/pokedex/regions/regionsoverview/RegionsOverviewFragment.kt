package com.vishalag53.pokedex.regions.regionsoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentRegionsOverviewBinding

class RegionsOverviewFragment : Fragment() {

    lateinit var binding: FragmentRegionsOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_regions_overview,container,false)
        val view = binding.root

        return view

    }


}