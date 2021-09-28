package com.example.melnyk_sportea_app.data.source.local

import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.model.Statistics

class LocalDataSource(
    val statisticsDao: StatisticsDao
) {
    suspend fun addStatisticsRecord(statisticsRecord: Statistics) {
        statisticsDao.addStatisticsRecord(statisticsRecord)
    }

    suspend fun removeAllStatistics() {
        statisticsDao.removeAllStatistics()
    }

    val getAllStatisticsRecords: List<Statistics> =
        statisticsDao.getAllStatisticsRecords()
}