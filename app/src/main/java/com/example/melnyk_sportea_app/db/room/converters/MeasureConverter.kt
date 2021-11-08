package com.example.melnyk_sportea_app.db.room.converters

import androidx.room.TypeConverter
import com.example.melnyk_sportea_app.utils.Measure

class MeasureConverter {
    @TypeConverter
    fun toMeasure(value: String) = enumValueOf<Measure>(value)

    @TypeConverter
    fun fromMeasure(value: Measure) = value.name
}