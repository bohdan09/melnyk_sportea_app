package com.example.melnyk_sportea_app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.melnyk_sportea_app.di.AppComponent
import com.example.melnyk_sportea_app.di.DaggerAppComponent
import com.example.melnyk_sportea_app.di.module.ApiModule
import com.example.melnyk_sportea_app.di.module.DataModule
import com.example.melnyk_sportea_app.di.module.UnitModule
import com.example.melnyk_sportea_app.di.module.UseCaseModule

class App : Application() {
    private lateinit var appComponent: AppComponent

    companion object {
        const val CHANNEL_ID = "trainingMode"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        appComponent = DaggerAppComponent.builder()
            .dataModule(DataModule(this))
            .apiModule(ApiModule(this))
            .useCaseModule(UseCaseModule(this))
            .unitModule(UnitModule())
            .build()
    }

    fun getAppComponent(): AppComponent = appComponent

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, "training", NotificationManager.IMPORTANCE_HIGH)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}