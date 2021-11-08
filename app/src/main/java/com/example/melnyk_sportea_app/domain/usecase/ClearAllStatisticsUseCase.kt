package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl

class ClearAllStatisticsUseCase(
    private val localDataSourceImpl: LocalDataSourceImpl
) {
    suspend fun execute() {
        localDataSourceImpl.clearAllStatistics()
    }
}