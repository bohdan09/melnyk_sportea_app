package com.example.melnyk_sportea_app.db.room.database

import android.content.Context
import androidx.room.*
import com.example.melnyk_sportea_app.db.room.Constants.APP_DATABASE_NAME
import com.example.melnyk_sportea_app.db.room.Converter
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.model.Statistics

@Database(entities = [Statistics::class], version = 1)
@TypeConverters(value = [Converter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getStatisticsDao(): StatisticsDao

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