package com.example.melnyk_sportea_app.db.room

import androidx.room.TypeConverter
import com.example.melnyk_sportea_app.utils.ProgramLevel

class LevelConverter {
    @TypeConverter
    fun toProgramLevel(value: String?) = value?.let { enumValueOf<ProgramLevel>(it) }

    @TypeConverter
    fun fromProgramLevel(value: ProgramLevel?) = value?.name
}