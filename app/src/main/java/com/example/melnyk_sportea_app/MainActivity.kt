package com.example.melnyk_sportea_app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
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
        bottomNavigationView.setupWithNavController(navigationController)
    }

    private fun bottomNavigationViewVisibility(
        navController: NavController,
        bottomNavigation: BottomNavigationView
    ) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.mapsFragment,
                R.id.profileFragment,
                R.id.statisticsFragment -> showBottomNavigationView(bottomNavigation)
                else -> hideBottomNavigationView(bottomNavigation)
            }
        }
    }

    private fun showBottomNavigationView(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNavigationView(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.visibility = View.GONE
    }
}