package com.example.melnyk_sportea_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.repository.TrainingJournalRepository

class GetTrainingJournalLiveDataUseCase(
    private val trainingJournalRepository: TrainingJournalRepository
) {
    fun execute() : LiveData<List<TrainingJournal>> {
        return trainingJournalRepository.getTrainingJournalLiveData()
    }
}