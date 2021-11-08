package com.example.melnyk_sportea_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.melnyk_sportea_app.db.room.Constant.QUOTE_TABLE_NAME

@Entity(tableName = QUOTE_TABLE_NAME)
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val author: String
)
