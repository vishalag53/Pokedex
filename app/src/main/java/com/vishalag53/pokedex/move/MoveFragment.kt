package com.vishalag53.pokedex.move

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentAbilityBinding
import com.vishalag53.pokedex.databinding.FragmentMoveBinding

class MoveFragment : Fragment() {

    lateinit var binding: FragmentMoveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_move,container,false)
        val view = binding.root

        return view
    }

}