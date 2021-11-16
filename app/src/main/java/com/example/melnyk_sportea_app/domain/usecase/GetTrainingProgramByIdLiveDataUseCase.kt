package com.example.melnyk_sportea_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.repository.TrainingProgramsRepository

class GetTrainingProgramByIdLiveDataUseCase(
    private val trainingProgramsRepository: TrainingProgramsRepository
) {
    fun execute(id: Int): LiveData<TrainingProgram> {
        return trainingProgramsRepository.getTrainingProgramById(id)
    }
}