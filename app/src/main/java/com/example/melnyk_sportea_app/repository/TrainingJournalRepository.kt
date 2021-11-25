package com.example.melnyk_sportea_app.repository

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.data.source.LocalDataSource
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.model.TrainingProgram

class TrainingJournalRepository(
        private val localDataSource: LocalDataSource
) {
    suspend fun clearTrainingJournal() {
        localDataSource.clearTrainingJournal()
    }

    fun getTrainingJournalLiveData(): LiveData<List<TrainingJournal>> {
        return localDataSource.getTrainingJournalLiveData()
    }

    suspend fun addTraining(training: TrainingJournal) {
        localDataSource.addTraining(training)
    }

    suspend fun addTrainingProgram(program: TrainingProgram) {
        localDataSource.addTrainingProgram(program)
    }
}