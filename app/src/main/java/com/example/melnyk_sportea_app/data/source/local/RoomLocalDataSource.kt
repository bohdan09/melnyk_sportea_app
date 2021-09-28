package com.example.melnyk_sportea_app.data.source.local

import com.example.melnyk_sportea_app.data.source.LocalDataSource
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.model.Statistics

class RoomLocalDataSource(
    private val statisticsDao: StatisticsDao
) : LocalDataSource {
    override suspend fun addStatisticsRecord(statisticsRecord: Statistics) {
        statisticsDao.addStatisticsRecord(statisticsRecord)
    }

    override suspend fun removeAllStatistics() {
        statisticsDao.removeAllStatistics()
    }

    override fun getAllStatisticsRecords(): List<Statistics> =
        statisticsDao.getAllStatisticsRecords()
}