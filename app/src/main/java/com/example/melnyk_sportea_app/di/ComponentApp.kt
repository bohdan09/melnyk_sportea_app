package com.example.melnyk_sportea_app.di

import android.app.Application

class ComponentApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}