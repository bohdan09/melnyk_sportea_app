package com.example.melnyk_sportea_app.model

import com.example.melnyk_sportea_app.utils.Difficulty
import com.example.melnyk_sportea_app.utils.MuscleGroup
import java.net.URL

data class Exercise(
    val id: Int,
    val name: String,
    val muscleGroup: MuscleGroup,
    val imageUrl: String, //
    val description: String,
    val repeats: Int,
    val sets: Int,
    val weight: Float,
    val workTime: Long,
    val restTime: Long,
    val difficulty: Difficulty,
    val dayNum: Int,
    val kcal: Int,
)
