package com.example.melnyk_sportea_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.TrainingProgram

class GetTrainingProgramListUseCase(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) {
    fun execute(): LiveData<List<TrainingProgram>> {
        return remoteDataSourceImpl.getTrainingProgramList()
    }
}