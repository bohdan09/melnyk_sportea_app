package com.example.melnyk_sportea_app.db.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.melnyk_sportea_app.db.room.Constants.TRAINING_PROGRAM_TABLE_NAME
import com.example.melnyk_sportea_app.model.TrainingProgram

@Dao
interface TrainingProgramDao {
    @Insert(onConflict = REPLACE)
    suspend fun addTrainingProgram(program : TrainingProgram)

    @Query("SELECT * FROM $TRAINING_PROGRAM_TABLE_NAME")
    fun getTrainingProgramLiveData() : LiveData<List<TrainingProgram>>

}