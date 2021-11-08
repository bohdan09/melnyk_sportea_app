package com.example.melnyk_sportea_app.repository

import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.model.Statistics

class StatisticsRepository(
    private val localDataSourceImpl: LocalDataSourceImpl
) {

    suspend fun addStatisticsRecord(statisticsRecord: Statistics) {
        localDataSourceImpl.addStatisticsRecord(statisticsRecord)
    }

    suspend fun clearAllStatistics() {
        localDataSourceImpl.clearAllStatistics()
    }

    fun getAllStatisticsRecords(): List<Statistics> =
        localDataSourceImpl.getAllStatisticsRecords()

    suspend fun getStatisticsRecordByName(name: String): Statistics {
        return localDataSourceImpl.getStatisticsRecordByName(name)
    }
}