package com.example.melnyk_sportea_app.model


interface ExerciseManager {
    suspend fun setExerciseInfo(index: Int) {}
    fun setExerciseInfo() {}
}