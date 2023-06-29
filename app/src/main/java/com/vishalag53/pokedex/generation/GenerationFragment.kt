package com.vishalag53.pokedex.generation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentAbilityBinding
import com.vishalag53.pokedex.databinding.FragmentGenerationBinding


class GenerationFragment : Fragment() {

    lateinit var binding: FragmentGenerationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_generation,container,false)
        val view = binding.root

        return view
    }

}