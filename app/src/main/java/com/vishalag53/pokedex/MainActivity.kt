package com.vishalag53.pokedex

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.vishalag53.pokedex.databinding.ActivityMainBinding
import com.vishalag53.pokedex.favorite.FavoriteFragment

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        drawer()

    }

    fun drawer() {
        drawerLayout = binding.drawerLayout
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
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
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

//    Used the Save Args Arguments
//    private fun getShareIntent(): Intent {
//        val args =  .fromBundle(requireArguments())
//        /*val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.setType("text/plain")
//            .putExtra(Intent.EXTRA_TEXT,getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
//
//        return shareIntent*/
//
//        return ShareCompat.IntentBuilder.from(requireActivity())
//            .setText(getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
//            .setType("text/plain")
//            .intent
//    }

    private fun shareSuccess() {
        //startActivity((getShareIntent()))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.fav_menu,menu)

        val favMenu = menu?.findItem(R.id.favMenu)

        favMenu?.setOnMenuItemClickListener {
            navController.navigate(R.id.favoriteFragment)
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favoriteFragment -> {
                navController.navigate(R.id.favoriteFragment)
                true
            }
            R.id.share -> {
                shareSuccess()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


}