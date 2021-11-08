package com.example.melnyk_sportea_app.di.module

import android.app.Application
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.db.firebase.CloudStorageHelper
import com.example.melnyk_sportea_app.db.firebase.RealtimeDatabaseHelper
import com.example.melnyk_sportea_app.db.room.dao.QuoteDao
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.db.room.dao.TrainingJournalDao
import com.example.melnyk_sportea_app.db.room.dao.TrainingProgramDao
import com.example.melnyk_sportea_app.db.room.database.AppDatabase
import com.example.melnyk_sportea_app.repository.StatisticsRepository
import com.example.melnyk_sportea_app.repository.TrainingJournalRepository
import com.example.melnyk_sportea_app.shared.preferences.PreferencesClientImpl
import com.example.melnyk_sportea_app.shared.preferences.SharedPreferencesClient
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides

@Module
class DataModule(private val application: Application) {
    @Provides
    fun provideStatisticsDao(database: AppDatabase): StatisticsDao {
        return database.getStatisticsDao()
    }

    @Provides
    fun provideTrainingProgramDao(database: AppDatabase): TrainingProgramDao {
        return database.getTrainingProgramDao()
    }

    @Provides
    fun provideTrainingJournal(database: AppDatabase): TrainingJournalDao {
        return database.getTrainingJournalDao()
    }

    @Provides
    fun provideQuoteDao(database: AppDatabase): QuoteDao {
        return database.getQuoteDao()
    }

    @Provides
    fun providesLocalDataSource(
        statisticsDao: StatisticsDao,
        journalDao: TrainingJournalDao,
        quoteDao: QuoteDao,
        trainingProgramDao: TrainingProgramDao
    ): LocalDataSourceImpl {
        return LocalDataSourceImpl(statisticsDao, quoteDao, journalDao, trainingProgramDao)
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

    @Provides
    fun provideCloudStorage(): StorageReference {
        return CloudStorageHelper.getCloudStorageInstance()
    }

    @Provides
    fun provideStatisticsRepository(localDataSourceImpl: LocalDataSourceImpl): StatisticsRepository {
        return StatisticsRepository(localDataSourceImpl)
    }

    @Provides
    fun provideTrainingJournalRepository(localDataSourceImpl: LocalDataSourceImpl): TrainingJournalRepository {
        return TrainingJournalRepository(localDataSourceImpl)
    }

    @Provides
    fun providePreferencesClientImpl(sharedPreferencesClient: SharedPreferencesClient): PreferencesClientImpl {
        return PreferencesClientImpl(sharedPreferencesClient)
    }
}
