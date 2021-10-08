package com.example.melnyk_sportea_app.di

import com.example.melnyk_sportea_app.MainActivity
import com.example.melnyk_sportea_app.di.module.ApiModule
import com.example.melnyk_sportea_app.di.module.DataModule
import com.example.melnyk_sportea_app.navigation.fragments.home.HomeFragment
import com.example.melnyk_sportea_app.repository.PeriodicRequest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        DataModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(periodicRequest: PeriodicRequest)
}