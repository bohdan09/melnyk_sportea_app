package com.example.melnyk_sportea_app.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.melnyk_sportea_app.db.room.Constants
import com.example.melnyk_sportea_app.db.room.Constants.STATISTICS_TABLE_NAME
import com.example.melnyk_sportea_app.model.Statistics

@Dao
interface StatisticsDao {

    @Insert
    fun addStatisticsRecord(statisticsRecord: Statistics)

    @Query("SELECT * FROM $STATISTICS_TABLE_NAME")
    fun getAllStatisticsRecords(): List<Statistics>

}