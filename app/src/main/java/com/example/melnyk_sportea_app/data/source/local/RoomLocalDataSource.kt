package com.example.melnyk_sportea_app.data.source.local

import com.example.melnyk_sportea_app.data.source.LocalDataSource
import com.example.melnyk_sportea_app.db.room.dao.QuoteDao
import com.example.melnyk_sportea_app.db.room.dao.StatisticsDao
import com.example.melnyk_sportea_app.db.room.dao.TrainingJournalDao
import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.Statistics
import com.example.melnyk_sportea_app.model.TrainingJournal

class RoomLocalDataSource(
    private val statisticsDao: StatisticsDao,
    private val quoteDao: QuoteDao,
    private val trainingJournalDao: TrainingJournalDao
) : LocalDataSource {
    override suspend fun addStatisticsRecord(statisticsRecord: Statistics) {
        statisticsDao.addStatisticsRecord(statisticsRecord)
    }

    override suspend fun removeAllStatistics() {
        statisticsDao.removeAllStatistics()
    }

    override suspend fun removeTrainingJournal() {
        trainingJournalDao.removeTrainingJournal()
    }

    override fun getAllStatisticsRecords(): List<Statistics> =
        statisticsDao.getAllStatisticsRecords()

    override fun getQuote(): List<Quote> =
        quoteDao.getQuote()

    override fun getTrainingJournal(): List<TrainingJournal> =
        trainingJournalDao.getTrainingJournal()


    override suspend fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    override suspend fun addTraining(training: TrainingJournal) {
        trainingJournalDao.addTraining(training)
    }
}