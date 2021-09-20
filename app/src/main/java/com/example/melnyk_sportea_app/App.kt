package com.example.melnyk_sportea_app

import android.app.Application
import com.example.melnyk_sportea_app.di.AppComponent
import com.example.melnyk_sportea_app.di.DaggerAppComponent

class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}