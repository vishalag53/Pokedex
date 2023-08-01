package com.vishalag53.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vishalag53.pokedex.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawer()

        bottomNavigationView = binding.bnView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frameLayout) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.pokedexOverviewFragment,R.id.abilityOverviewFragment,R.id.favoriteOverviewFragment),binding.drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.pokedexOverviewFragment -> {
                    if (navController.currentDestination?.id == R.id.pokedexDetailFragment){
                        navController.popBackStack(R.id.pokedexOverviewFragment,false)
                    }
                    else{
                        navController.navigate(R.id.pokedexOverviewFragment)
                    }
                }
                R.id.abilityOverviewFragment -> {
                    if (navController.currentDestination?.id == R.id.abilityDetailFragment){
                        navController.popBackStack(R.id.abilityOverviewFragment,false)
                    }
                    else{
                        navController.navigate(R.id.abilityOverviewFragment)
                    }
                }
                R.id.favoriteOverviewFragment -> {
                    if (navController.currentDestination?.id == R.id.pokedexDetailFragment){
                        navController.popBackStack(R.id.favoriteOverviewFragment,false)
                    }
                    else{
                        navController.navigate(R.id.favoriteOverviewFragment)
                    }
                }
            }
            true
        }

    }

    private fun drawer() {
        drawerLayout = binding.drawerLayout
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frameLayout) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)

//        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
//            if (nd.id == nc.graph.startDestinationId) {
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
//            } else {
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
//            }
//        }

        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.frameLayout)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val navController = findNavController(R.id.frameLayout)
        if (navController.currentDestination?.id != R.id.pokedexOverviewFragment){
            navController.popBackStack()
        }
        else{
            super.onBackPressed()
        }

    }


}