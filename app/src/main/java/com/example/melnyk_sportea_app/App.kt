package com.example.melnyk_sportea_app

import android.app.Application
import com.example.melnyk_sportea_app.di.AppComponent
import com.example.melnyk_sportea_app.di.DaggerAppComponent
import com.example.melnyk_sportea_app.di.module.ApiModule
import com.example.melnyk_sportea_app.di.module.DataModule
import com.example.melnyk_sportea_app.di.module.UnitModule
import com.example.melnyk_sportea_app.di.module.UseCaseModule

class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .dataModule(DataModule(this))
            .apiModule(ApiModule(this))
            .useCaseModule(UseCaseModule(this))
            .unitModule(UnitModule())
            .build()
    }

    fun getAppComponent(): AppComponent = appComponent
}