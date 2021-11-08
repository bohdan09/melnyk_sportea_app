package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.repository.TrainingProgramsRepository

class CacheTrainingProgramsUseCase(
    private val trainingProgramsRepository: TrainingProgramsRepository
) {
    suspend fun execute(programs: List<TrainingProgram>) {
        trainingProgramsRepository.cacheProgramsToDb(programs)
    }
}