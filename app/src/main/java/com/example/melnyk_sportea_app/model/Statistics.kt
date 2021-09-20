package com.example.melnyk_sportea_app.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.melnyk_sportea_app.enum.Measure

@Entity
data class Statistics(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val value: Float,
    val measure: Measure,
    val date: Long
)