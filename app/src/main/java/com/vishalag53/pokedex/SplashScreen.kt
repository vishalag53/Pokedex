package com.vishalag53.pokedex

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.vishalag53.pokedex.databinding.ActivitySplashScreenBinding
import androidx.navigation.NavInflater

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash_screen)


//        Handler(Looper.getMainLooper()).postDelayed({
//            fun run() {
//                val iHome = Intent(this@SplashScreen, MainActivity::class.java)
//                startActivity(iHome)
//                finish()
//            }
//        },3000)


        val intent = Intent(this, MainActivity::class.java)


        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 3000)

        anim()

    }

    private fun anim() {
        val animTop = AnimationUtils.loadAnimation(binding.root.context,R.anim.from_top)
        val animBottom =AnimationUtils.loadAnimation(binding.root.context,R.anim.from_bottom)

        binding.imgView.animation = animBottom
        binding.imageView.animation = animTop
    }

}