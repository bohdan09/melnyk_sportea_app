package com.example.melnyk_sportea_app.data.source

import android.net.Uri
import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.TrainingProgram

interface RemoteDataSource {
    suspend fun getQuotes(): List<Quote>

    fun getTrainingProgramList() : List<TrainingProgram>
}