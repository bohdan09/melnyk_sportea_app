package com.example.melnyk_sportea_app.db.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.melnyk_sportea_app.db.room.Constant.APP_DATABASE_NAME
import com.example.melnyk_sportea_app.db.room.converters.ExerciseConverter
import com.example.melnyk_sportea_app.db.room.converters.MeasureConverter
import com.example.melnyk_sportea_app.db.room.dao.QuoteDao
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.db.room.dao.TrainingJournalDao
import com.example.melnyk_sportea_app.db.room.dao.TrainingProgramDao
import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.Statistics
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.model.TrainingProgram

@Database(
    entities = [Statistics::class, Quote::class, TrainingJournal::class, TrainingProgram::class],
    version = 2
)
@TypeConverters(value = [MeasureConverter::class, ExerciseConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getStatisticsDao(): StatisticsDao
    abstract fun getTrainingJournalDao(): TrainingJournalDao
    abstract fun getQuoteDao(): QuoteDao
    abstract fun getTrainingProgramDao(): TrainingProgramDao

    companion object {
        private var databaseInstance: AppDatabase? = null

        fun getAppDatabaseInstance(context: Context): AppDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    APP_DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return databaseInstance!!
        }
    }
}