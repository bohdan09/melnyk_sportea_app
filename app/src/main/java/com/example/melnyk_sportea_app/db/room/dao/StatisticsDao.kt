package com.example.melnyk_sportea_app.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.melnyk_sportea_app.model.Statistics

@Dao
interface StatisticsDao {
    companion object {
        const val STATISTICS_TABLE_NAME = "Statistics"
    }

    @Insert
    fun addStatisticsRecord(statisticsRecord: Statistics)

    @Query("SELECT * FROM ${STATISTICS_TABLE_NAME}")
    fun getAllStatisticsRecords(): List<Statistics>

}