package com.vishalag53.pokedex

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    lateinit var binding: FragmentSplashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.navigate_splashScreenFragment_to_homeSplashScreenFragment)
        },3000)

        binding = FragmentSplashScreenBinding.inflate(layoutInflater)

        //binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash_screen,container,false)

        anim()

        return binding.root
    }

    private fun anim() {
        val animTop = AnimationUtils.loadAnimation(binding.root.context,R.anim.from_top)
        val animBottom =AnimationUtils.loadAnimation(binding.root.context,R.anim.from_bottom)

        binding.imgView.animation = animBottom
        binding.imageView.animation = animTop
    }

}