package com.vishalag53.pokedex.splashScreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.vishalag53.pokedex.R
import com.vishalag53.pokedex.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    private var isFirstTime = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash_screen,container,false)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashScreenFragmentDirections.navigateSplashScreenFragmentToMainActivity())
        },2000)

        anim()

        return binding.root
    }



    private fun anim() {
        val animTop = AnimationUtils.loadAnimation(binding.root.context, R.anim.from_top)
        val animBottom = AnimationUtils.loadAnimation(binding.root.context, R.anim.from_bottom)

        binding.imgView.animation = animBottom
        binding.imageView.animation = animTop
    }


}