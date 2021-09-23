package com.example.melnyk_sportea_app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.melnyk_sportea_app.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }


        val navigationController = findNavController(R.id.nav_controller)
        val bottomNavigationView = binding.navView
        bottomNavigationViewVisibility(navigationController, bottomNavigationView)
        var appBarConfig = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.mapsFragment,
                R.id.statisticsFragment,
                R.id.profileFragment,
                R.id.exerciseListFragment,
                R.id.exerciseDescriptionFragment,
                R.id.finishTrainingFragment,
                R.id.preparationFragment,
                R.id.restFragment,
                R.id.trainingFragment,
                R.id.profileFragment,
                R.id.statisticsFragment
            )
        )

        setupActionBarWithNavController(navigationController, appBarConfig)
        bottomNavigationView.setupWithNavController(navigationController)
    }


    fun bottomNavigationViewVisibility(
        navController: NavController,
        bottomNavigationView: BottomNavigationView
    ) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNavigationView(bottomNavigationView)
                R.id.mapsFragment -> showBottomNavigationView(bottomNavigationView)
                R.id.profileFragment -> showBottomNavigationView(bottomNavigationView)
                R.id.statisticsFragment -> showBottomNavigationView(bottomNavigationView)
                else -> hideBottomNavigationView(bottomNavigationView)
            }
        }
    }

    fun showBottomNavigationView(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideBottomNavigationView(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.visibility = View.GONE
    }

}