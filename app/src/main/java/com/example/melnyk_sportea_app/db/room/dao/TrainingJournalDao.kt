package com.example.melnyk_sportea_app.db.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.melnyk_sportea_app.db.room.Constants.JOURNAL_TABLE_NAME
import com.example.melnyk_sportea_app.model.TrainingJournal

@Dao
interface TrainingJournalDao {
    @Insert
    suspend fun addTraining(trainingProgram: TrainingJournal)

    @Query("SELECT * FROM $JOURNAL_TABLE_NAME")
    fun getTrainingJournal(): LiveData<List<TrainingJournal>>

    @Query("DELETE FROM $JOURNAL_TABLE_NAME")
    suspend fun clearTrainingJournal()

}