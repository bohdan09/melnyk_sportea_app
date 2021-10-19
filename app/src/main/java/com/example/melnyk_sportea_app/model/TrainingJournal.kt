package com.example.melnyk_sportea_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.melnyk_sportea_app.db.room.Constants.JOURNAL_TABLE_NAME

@Entity(tableName = JOURNAL_TABLE_NAME)
data class TrainingJournal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val programId: Int,
    val programName: String,
    val date: Long,
    val duration: Long,
    val kcal: Int
)