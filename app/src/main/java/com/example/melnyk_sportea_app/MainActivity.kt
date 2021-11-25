package com.example.melnyk_sportea_app

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import androidx.work.Data
import com.example.melnyk_sportea_app.databinding.ActivityMainBinding
import com.example.melnyk_sportea_app.model.wrapper.Quotes
import com.example.melnyk_sportea_app.mvp.MainPresenter
import com.example.melnyk_sportea_app.mvp.MainViewInterface
import com.example.melnyk_sportea_app.utils.Reminder
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), MainViewInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var decorView: View
    private lateinit var navigationController: NavController
    lateinit var mainPresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        navigationController = findNavController(R.id.nav_controller)
        val bottomNavigationView = binding.navView
        decorView = window.decorView

        bottomNavigationViewVisibility(bottomNavigationView)
        bottomNavigationView.setupWithNavController(navigationController)

        setupMVP()
        getQuotes()
        // hideBottomNavigationBar(navigationController)

    }

    private fun bottomNavigationViewVisibility(
        bottomNavigation: BottomNavigationView
    ) {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.settingsFragment,
                R.id.profileFragment,
                R.id.historyFragment -> showBottomNavigationView(bottomNavigation)
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

    private fun startReminder(data: Data) {
        Reminder.periodicRequest(this, data)
    }

    private fun stopReminder() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val notification = prefs.getBoolean("sendNotification", true)
        Log.d("TAG", "stopReminder: $notification")
        Reminder.cancelWork(this, flag = notification)
    }

    private fun setupMVP() {
        mainPresenter = MainPresenter(this)
    }

    private fun getQuotes() {
        mainPresenter.getQuotes()
    }

    override fun displayQuotes(quotes: Quotes) {
        val data = Data.Builder().putString("quote", quotes[Random.nextInt(200)].text).build()
        startReminder(data)
        stopReminder()
    }

    override fun displayError(s: String?) {
        Log.d("TAG", s!!)
    }

    private fun hideBottomNavigationBar() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.restFragment,
                R.id.trainingFragment,
                R.id.preparationFragment -> {
                    val ui_Options = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

                    decorView.systemUiVisibility = ui_Options
                }

                else -> {
                    decorView.systemUiVisibility = View.VISIBLE
                }
            }
        }

    }
}
