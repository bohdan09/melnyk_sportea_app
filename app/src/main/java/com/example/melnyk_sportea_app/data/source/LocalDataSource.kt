package com.example.melnyk_sportea_app.data.source

import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.Statistics
import com.example.melnyk_sportea_app.model.TrainingJournal

interface LocalDataSource {
    suspend fun addStatisticsRecord(statisticsRecord: Statistics)

    suspend fun addQuote(quote: Quote)

    suspend fun addTraining(training: TrainingJournal)

    suspend fun clearAllStatistics()

    suspend fun clearTrainingJournal()

    fun getAllStatisticsRecords(): List<Statistics>

    fun getQuotes(): List<Quote>

    fun getTrainingJournal(): List<TrainingJournal>

    suspend fun removeQuote(quote: Quote)

    suspend fun getStatisticsRecordByName(name: String): Statistics
}