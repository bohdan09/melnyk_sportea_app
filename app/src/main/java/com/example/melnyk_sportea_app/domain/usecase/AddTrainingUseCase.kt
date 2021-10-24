package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.repository.TrainingJournalRepository

class AddTrainingUseCase(
    private val trainingJournalRepository: TrainingJournalRepository
) {
    suspend fun execute(trainingJournal: TrainingJournal) {
        trainingJournalRepository.addTraining(trainingJournal)
    }
}