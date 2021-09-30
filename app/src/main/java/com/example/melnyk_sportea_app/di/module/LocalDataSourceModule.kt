package com.example.melnyk_sportea_app.di.module

import com.example.melnyk_sportea_app.db.room.dao.QuoteDao
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.db.room.dao.TrainingJournalDao
import com.example.melnyk_sportea_app.db.room.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class LocalDataSourceModule {
    @Provides
    fun provideStatisticsDao(database: AppDatabase): StatisticsDao {
        return database.getStatisticsDao()
    }

    @Provides
    fun provideQuoteDao(database: AppDatabase): QuoteDao {
        return database.getQuoteDao()
    }

    @Provides
    fun provideTrainingJournal(database: AppDatabase): TrainingJournalDao {
        return database.getTrainingJournalDao()
    }
}