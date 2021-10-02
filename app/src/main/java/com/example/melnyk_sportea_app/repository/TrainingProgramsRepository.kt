package com.example.melnyk_sportea_app.repository

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.TrainingProgram

class TrainingProgramsRepository(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) {
    fun getTrainingProgramList(): LiveData<List<TrainingProgram>> {
        return remoteDataSourceImpl.getTrainingProgramList()
    }
}