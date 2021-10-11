package com.example.melnyk_sportea_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.melnyk_sportea_app.db.room.Constants.TRAINING_PROGRAM_TABLE_NAME
import com.example.melnyk_sportea_app.utils.ProgramLevel

@Entity(tableName = TRAINING_PROGRAM_TABLE_NAME)
data class TrainingProgram(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val programName: String? = null,
    val imageUrl: String? = null,
    val level: ProgramLevel? = null,
    val exercises: List<Exercise>? = null
)
