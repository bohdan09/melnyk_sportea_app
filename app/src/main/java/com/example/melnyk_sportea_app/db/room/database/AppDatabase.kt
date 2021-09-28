package com.example.melnyk_sportea_app.db.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.melnyk_sportea_app.db.room.Constants.APP_DATABASE_NAME
import com.example.melnyk_sportea_app.db.room.Converter
import com.example.melnyk_sportea_app.db.room.dao.QuoteDao
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.db.room.dao.TrainingJournalDao
import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.Statistics
import com.example.melnyk_sportea_app.model.TrainingJournal

@Database(entities = [Statistics::class, Quote::class, TrainingJournal::class], version = 2)
@TypeConverters(value = [Converter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getStatisticsDao(): StatisticsDao
    abstract fun getTrainingJournalDao(): TrainingJournalDao
    abstract fun getQuoteDao(): QuoteDao

    companion object {
        private var databaseInstance: AppDatabase? = null

        fun getAppDatabaseInstance(context: Context): AppDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    APP_DATABASE_NAME
                ).build()
            }
            return databaseInstance!!
        }
    }
}