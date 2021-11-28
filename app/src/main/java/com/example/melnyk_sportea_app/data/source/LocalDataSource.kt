package com.example.melnyk_sportea_app.data.source

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.Statistics
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.model.TrainingProgram

interface LocalDataSource {
    suspend fun addStatisticsRecord(statisticsRecord: Statistics)

    suspend fun addQuote(quote: Quote)

    suspend fun addTraining(training: TrainingJournal)

    suspend fun clearAllStatistics()

    suspend fun clearTrainingJournal()

    fun getAllStatisticsRecords(): List<Statistics>

    fun getQuotes(): List<Quote>

    fun getTrainingJournalLiveData(): LiveData<List<TrainingJournal>>

    suspend fun removeQuote(quote: Quote)

    suspend fun getStatisticsRecordByName(name: String): Statistics

    suspend fun addTrainingProgram(program: TrainingProgram)

    fun getTrainingProgramLiveData(): LiveData<List<TrainingProgram>>

    fun getTrainingProgramById(id: Int): LiveData<TrainingProgram>
}