package com.example.melnyk_sportea_app.di.module

import android.app.Application
import com.example.melnyk_sportea_app.db.firebase.RealtimeDatabaseHelper
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.db.room.database.AppDatabase
import com.example.melnyk_sportea_app.shared.preferences.SharedPreferencesClient
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides

@Module
class DataModule(private val application: Application) {

    @Provides
    fun provideStatisticsDao(database: AppDatabase): StatisticsDao {
        return database.getStatisticsDao()
    }

    @Provides
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase.getAppDatabaseInstance(application.applicationContext)
    }

    @Provides
    fun provideSharedPreferences(): SharedPreferencesClient {
        return SharedPreferencesClient()
    }

    @Provides
    fun provideRealtimeDatabase(): DatabaseReference {
        return RealtimeDatabaseHelper.getRealtimeDatabaseInstance()
    }
}
