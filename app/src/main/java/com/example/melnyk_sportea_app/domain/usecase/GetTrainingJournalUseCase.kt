package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl

class GetTrainingJournalUseCase(
    private val localDataSourceImpl: LocalDataSourceImpl
) {
    fun execute() {
        localDataSourceImpl.getTrainingJournal()
    }
}