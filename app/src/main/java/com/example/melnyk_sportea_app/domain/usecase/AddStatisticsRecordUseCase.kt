package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.model.Statistics

class AddStatisticsRecordUseCase(
    private val localDataSourceImpl: LocalDataSourceImpl
) {
    suspend fun execute(statisticsRecord: Statistics) {
        localDataSourceImpl.addStatisticsRecord(statisticsRecord)
    }
}