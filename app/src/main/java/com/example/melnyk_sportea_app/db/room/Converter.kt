package com.example.melnyk_sportea_app.db.room

import androidx.room.TypeConverter
import com.example.melnyk_sportea_app.utils.Measure


class Converter {
    @TypeConverter
    fun toMeasure(value: String) = enumValueOf<Measure>(value)

    @TypeConverter
    fun fromMeasure(value: Measure) = value.name
}