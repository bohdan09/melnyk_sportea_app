package com.example.melnyk_sportea_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.repository.TrainingProgramsRepository

class GetTrainingProgramListUseCase(
    private val trainingProgramsRepository: TrainingProgramsRepository
) {
    fun execute(): LiveData<List<TrainingProgram>> {
        return trainingProgramsRepository.getTrainingProgramList()
    }
}