package com.example.melnyk_sportea_app.domain.usecase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.api.InternetConnection
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.shared.preferences.PreferencesClientImpl

class GetTrainingProgramListUseCase(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val preferencesClientImpl: PreferencesClientImpl,
    private val context: Context,
    private val internetConnection: InternetConnection
) {
    fun execute(): LiveData<List<TrainingProgram>> {
        val connection = internetConnection.checkConnection(context)
        return if (connection) {
            remoteDataSourceImpl.getTrainingProgramList()
        } else preferencesClientImpl.getTrainingProgramLiveData(context)
    }


}