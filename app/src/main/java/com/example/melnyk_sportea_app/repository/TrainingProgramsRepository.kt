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
            Log.d("TAG", "connect")
            val programLiveData =remoteDataSourceImpl.getTrainingProgramList()
            //cacheProgramsToDB(programLiveData)
            //programLiveData
            localDataSourceImpl.getTrainingProgramLiveData()
        } else{
            Log.d("TAG", "disconnect")
            localDataSourceImpl.getTrainingProgramLiveData()
        }


    }


    suspend fun cache(list: List<TrainingProgram>){
        for (i in list.indices){
            Log.d("TAG", "Repository cache")
            localDataSourceImpl.addTrainingProgram(list[i])
        }
    }


}