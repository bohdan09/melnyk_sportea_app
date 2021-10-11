package com.example.melnyk_sportea_app.model

import com.example.melnyk_sportea_app.utils.Difficulty
import com.example.melnyk_sportea_app.utils.MuscleGroup
import java.net.URL

data class Exercise(
    val id: Int? = null,
    val name: String? = null,
    val muscleGroup: MuscleGroup? = null,
    val imageUrl: String? = null, //
    val description: String? = null,
    val repeats: Int? = null,
    val sets: Int? = null,
    val weight: Float? = null,
    val workTime: Long? = null,
    val restTime: Long? = null,
    val dayNum: Int? = null,
    val kcal: Int? = null,
)
