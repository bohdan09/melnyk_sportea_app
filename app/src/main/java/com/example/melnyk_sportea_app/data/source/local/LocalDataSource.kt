package com.example.melnyk_sportea_app.data.source.local

import com.example.melnyk_sportea_app.data.source.Local
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.model.Statistics

class LocalDataSource(
    private val statisticsDao: StatisticsDao
) : Local {
    override suspend fun addStatisticsRecord(statisticsRecord: Statistics) {
        statisticsDao.addStatisticsRecord(statisticsRecord)
    }

    override suspend fun removeAllStatistics() {
        statisticsDao.removeAllStatistics()
    }

    override fun getAllStatisticsRecords(): List<Statistics> =
        statisticsDao.getAllStatisticsRecords()
}