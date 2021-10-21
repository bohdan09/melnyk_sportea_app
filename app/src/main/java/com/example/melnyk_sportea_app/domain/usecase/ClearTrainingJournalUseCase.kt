package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.repository.TrainingJournalRepository

class ClearTrainingJournalUseCase(
    private val trainingJournalRepository: TrainingJournalRepository
) {
    suspend fun execute() {
        trainingJournalRepository.clearTrainingJournal()
    }
}