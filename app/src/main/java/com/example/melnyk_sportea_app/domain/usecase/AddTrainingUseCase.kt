package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.model.TrainingJournal

class AddTrainingUseCase(
    private val localDataSourceImpl: LocalDataSourceImpl
) {
    suspend fun execute(trainingJournal: TrainingJournal) {
        localDataSourceImpl.addTraining(trainingJournal)
    }
}