package com.example.melnyk_sportea_app.module

import android.app.Application
import android.content.Context
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.db.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(val application: Application) {

    @Singleton
    @Provides
    fun provideStatisticsDao(database: AppDatabase): StatisticsDao {
        return database.getStatisticsDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getAppDatabaseInstance(context)
    }

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }
}