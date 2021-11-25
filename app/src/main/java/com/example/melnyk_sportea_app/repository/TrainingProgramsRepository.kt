package com.example.melnyk_sportea_app.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.api.InternetConnection
import com.example.melnyk_sportea_app.data.source.LocalDataSource
import com.example.melnyk_sportea_app.data.source.RemoteDataSource
import com.example.melnyk_sportea_app.model.TrainingProgram

class TrainingProgramsRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val context: Context,
        private val internetConnection: InternetConnection
) {
    fun getTrainingProgramList(): LiveData<List<TrainingProgram>> {
        val connection = internetConnection.checkConnection(context)
        return if (connection) {
            remoteDataSource.getTrainingProgramList()
        } else {
            localDataSource.getTrainingProgramLiveData()
        }
    }

    suspend fun cacheProgramsToDb(programs: List<TrainingProgram>) {
        val connection = internetConnection.checkConnection(context)
        if (connection) {
            for (i in programs.indices) {
                localDataSource.addTrainingProgram(programs[i])
            }
        }
    }

    fun getTrainingProgramById(id: Int): LiveData<TrainingProgram> {
        return localDataSource.getTrainingProgramById(id)
    }

}