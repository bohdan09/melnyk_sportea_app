package com.example.melnyk_sportea_app.data.source.local

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.data.source.LocalDataSource
import com.example.melnyk_sportea_app.db.room.dao.QuoteDao
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.db.room.dao.TrainingJournalDao
import com.example.melnyk_sportea_app.db.room.dao.TrainingProgramDao
import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.Statistics
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.model.TrainingProgram

class LocalDataSourceImpl(
    private val statisticsDao: StatisticsDao,
    private val quoteDao: QuoteDao,
    private val trainingJournalDao: TrainingJournalDao,
    private val trainingProgramDao: TrainingProgramDao
) : LocalDataSource {
    override suspend fun addStatisticsRecord(statisticsRecord: Statistics) {
        statisticsDao.addStatisticsRecord(statisticsRecord)
    }

    override suspend fun clearAllStatistics() {
        statisticsDao.clearAllStatistics()
    }

    override suspend fun clearTrainingJournal() {
        trainingJournalDao.clearTrainingJournal()
    }

    override fun getAllStatisticsRecords(): List<Statistics> =
        statisticsDao.getAllStatisticsRecords()

    override fun getQuotes(): List<Quote> =
        quoteDao.getQuotes()

    override fun getTrainingJournalLiveData(): LiveData<List<TrainingJournal>> =
        trainingJournalDao.getTrainingJournal()


    override suspend fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    override suspend fun addTraining(training: TrainingJournal) {
        trainingJournalDao.addTraining(training)
    }

    override suspend fun removeQuote(quote: Quote) {
        quoteDao.removeQuote(quote)
    }

    override suspend fun getStatisticsRecordByName(name: String): Statistics {
        return statisticsDao.getStatisticsRecordByName(name)
    }

    override suspend fun addTrainingProgram(program: TrainingProgram) {
        trainingProgramDao.addTrainingProgram(program)
    }

    override fun getTrainingProgramLiveData(): LiveData<List<TrainingProgram>> {
        return trainingProgramDao.getTrainingProgramLiveData()
    }
}