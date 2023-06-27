package com.vishalag53.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vishalag53.pokedex.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}