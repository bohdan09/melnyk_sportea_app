package com.example.melnyk_sportea_app.repository

import com.example.melnyk_sportea_app.data.source.LocalDataSource
import com.example.melnyk_sportea_app.model.Statistics

class StatisticsRepository(
        private val localDataSource: LocalDataSource
) {

    suspend fun addStatisticsRecord(statisticsRecord: Statistics) {
        localDataSource.addStatisticsRecord(statisticsRecord)
    }

    suspend fun clearAllStatistics() {
        localDataSource.clearAllStatistics()
    }

    fun getAllStatisticsRecords(): List<Statistics> =
            localDataSource.getAllStatisticsRecords()

    suspend fun getStatisticsRecordByName(name: String): Statistics {
        return localDataSource.getStatisticsRecordByName(name)
    }
}