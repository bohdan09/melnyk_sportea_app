package com.example.melnyk_sportea_app.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.api.InternetConnection
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.TrainingProgram

class TrainingProgramsRepository(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl,
    private val context: Context,
    private val internetConnection: InternetConnection
) {
    fun getTrainingProgramList(): LiveData<List<TrainingProgram>> {
        val connection = internetConnection.checkConnection(context)
        return if (connection) {
            remoteDataSourceImpl.getTrainingProgramList()
        } else {
            Log.d("TAG", "disconnect")
            localDataSourceImpl.getTrainingProgramLiveData()
        }
    }

    suspend fun cacheProgramsToDb(programs: List<TrainingProgram>) {
//        if (localDataSourceImpl.getTrainingProgramLiveData().value == null) {
//            for (i in programs.indices) {
//                localDataSourceImpl.addTrainingProgram(programs[i])
//            }
//        }
    }


}