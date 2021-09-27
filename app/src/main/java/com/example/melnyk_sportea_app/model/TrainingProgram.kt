package com.example.melnyk_sportea_app.model

data class TrainingProgram(
    val id: Int,
    val programName: String,
    val exercises : List<Exercise>
)
