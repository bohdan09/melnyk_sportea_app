package com.example.melnyk_sportea_app.model

import com.example.melnyk_sportea_app.utils.ProgramLevel

data class TrainingProgram(
    val id: Int? = null,
    val programName: String? = null,
    val imageUrl: String? = null,
    val level: ProgramLevel? = null,
    val exercises: List<Exercise>? = null
)
