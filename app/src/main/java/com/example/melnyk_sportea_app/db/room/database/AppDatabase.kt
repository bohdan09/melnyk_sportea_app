package com.example.melnyk_sportea_app.db.room.database

import android.content.Context
import androidx.room.*
import com.example.melnyk_sportea_app.converter.Converter
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.model.Statistics

@Database(entities = arrayOf(Statistics::class), version = 1)
@TypeConverters(value = [Converter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun getStatisticsDao(): StatisticsDao

    companion object {
        private var DATABASE_INSTANCE: AppDatabase? = null
        private const val APP_DATABASE_NAME = "AppDatabase"
        const val STATISTICS_TABLE_NAME = "Statistics"

        fun getAppDatabaseInstance(context: Context): AppDatabase {
            if (DATABASE_INSTANCE == null) {
                DATABASE_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    APP_DATABASE_NAME
                )
                    .build()
            }
            return DATABASE_INSTANCE!!
        }
    }


}