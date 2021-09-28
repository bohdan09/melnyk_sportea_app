package com.example.melnyk_sportea_app.data.source

import com.example.melnyk_sportea_app.model.Statistics

interface Local {
    suspend fun addStatisticsRecord(statisticsRecord: Statistics) {}

    suspend fun removeAllStatistics() {}

    fun getAllStatisticsRecords(): List<Statistics>
}