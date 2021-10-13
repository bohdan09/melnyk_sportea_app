package com.example.melnyk_sportea_app.domain.usecase

import android.util.Log
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.repository.TrainingProgramsRepository

class CacheTrainingProgramUseCase(
    private val trainingProgramsRepository: TrainingProgramsRepository
) {
    suspend fun execute(list: List<TrainingProgram>){
        Log.d("TAG", "Use case cache")
        trainingProgramsRepository.cache(list)
    }
}