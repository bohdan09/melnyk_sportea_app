package com.example.melnyk_sportea_app.db.room.converters

import androidx.room.TypeConverter
import com.example.melnyk_sportea_app.model.Exercise
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ExerciseConverter {
    @TypeConverter
    fun toExercise(value: String): List<Exercise> {
        val exercise =
            Gson().fromJson(value, object : TypeToken<List<Exercise>>() {}.type) as List<Exercise>
        return exercise
    }

    @TypeConverter
    fun fromExercise(value: List<Exercise>): String {
        val json = Gson().toJson(value)
        return json
    }
}