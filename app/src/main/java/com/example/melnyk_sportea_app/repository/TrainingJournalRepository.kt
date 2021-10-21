package com.example.melnyk_sportea_app.repository

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.model.TrainingProgram

class TrainingJournalRepository(
    private val localDataSourceImpl: LocalDataSourceImpl
) {
    suspend fun clearTrainingJournal() {
        localDataSourceImpl.clearTrainingJournal()
    }

    fun getTrainingJournalLiveData(): LiveData<List<TrainingJournal>> {
        return localDataSourceImpl.getTrainingJournalLiveData()
    }

    suspend fun addTraining(training: TrainingJournal) {
        localDataSourceImpl.addTraining(training)
    }

    suspend fun addTrainingProgram(program: TrainingProgram) {
        localDataSourceImpl.addTrainingProgram(program)
    }
}