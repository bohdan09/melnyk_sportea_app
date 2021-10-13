package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.model.TrainingProgram

class CacheTrainingProgramToDbUseCase
    (
    private val localDataSourceImpl: LocalDataSourceImpl
) {
        suspend fun execute(programList: List<TrainingProgram>){
            for (i in programList.indices){
                localDataSourceImpl.addTrainingProgram(programList[i])
            }
        }

}