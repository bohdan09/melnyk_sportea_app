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
        } else{
            Log.d("TAG", "disconnect")
            localDataSourceImpl.getTrainingProgramLiveData()
        }
    }

//    private suspend fun chooseDataSource() : LiveData<List<TrainingProgram>>{
//        return if(checkProgramsAvailability()){
//            localDataSourceImpl.getTrainingProgramLiveData()
//        }else {
//            val programs = remoteDataSourceImpl.getTrainingProgramList().value
//            cacheProgramsToDB(programList = programs)
//            localDataSourceImpl.getTrainingProgramLiveData()
//        }
//    }
//
//    private fun checkProgramsAvailability() : Boolean{
//        val programs = localDataSourceImpl.getTrainingProgramLiveData().value
//        return programs != null
//    }
//
//
//    suspend fun cacheProgramsToDB(programList: List<TrainingProgram>){
//        for (i in programList.indices){
//            Log.d("TAG", "Repository cache")
//            localDataSourceImpl.addTrainingProgram(programList[i])
//        }
//    }


}