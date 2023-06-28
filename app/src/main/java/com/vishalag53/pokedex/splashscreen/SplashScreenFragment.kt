package com.vishalag53.pokedex.splashscreen

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
import androidx.navigation.fragment.findNavController
import com.vishalag53.pokedex.R

class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.navigate_splashScreenFragment_to_homeSplashScreenFragment)
        },3000)
        val view = inflater.inflate(R.layout.fragment_splash_screen,container,false)

        val animTop = AnimationUtils.loadAnimation(view.context,R.anim.from_top)
        val animBottom =AnimationUtils.loadAnimation(view.context,R.anim.from_bottom)

        val txtSplash = view.findViewById<TextView>(R.id.txtView)
        val imgSplash = view.findViewById<ImageView>(R.id.imageView)

        txtSplash.animation = animBottom
        imgSplash.animation = animTop

        return view
    }

}