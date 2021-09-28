package com.example.melnyk_sportea_app.data.source

import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.Statistics
import com.example.melnyk_sportea_app.model.TrainingJournal

interface LocalDataSource {
    suspend fun addStatisticsRecord(statisticsRecord: Statistics)

    suspend fun addQuote(quote: Quote)

    suspend fun addTraining(training: TrainingJournal)

    suspend fun removeAllStatistics()

    suspend fun removeTrainingJournal()

    fun getAllStatisticsRecords(): List<Statistics>

    fun getQuote() : List<Quote>

    fun getTrainingJournal() : List<TrainingJournal>


}