package com.example.melnyk_sportea_app.domain.usecase

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.api.InternetConnection
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.shared.preferences.PreferencesClientImpl

class GetTrainingProgramListUseCase(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl,
    private val context: Context,
    private val internetConnection: InternetConnection
) {
    fun execute(): LiveData<List<TrainingProgram>> {
        val connection = internetConnection.checkConnection(context)
        return if (connection) {
            Log.d("TAG", "connect")
            val programLiveData =remoteDataSourceImpl.getTrainingProgramList()
            //cacheProgramsToDB(programLiveData)
          return  programLiveData
        } else{
            Log.d("TAG", "disconnect")
            localDataSourceImpl.getTrainingProgramLiveData()
        }
    }


//    private suspend fun cacheProgramsToDB(programList: LiveData<List<TrainingProgram>>){
//        val list = programList.value
//        Log.d("TAG", list?.size.toString())
//        for(i in 0..list?.size!!){
//            localDataSourceImpl.addTrainingProgram(list[i])
//        }
//    }
}