package com.example.melnyk_sportea_app.data.source

import androidx.lifecycle.LiveData
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.example.melnyk_sportea_app.model.wrapper.Quotes
import io.reactivex.Observable

interface RemoteDataSource {
    fun getQuotes(): Observable<Quotes>

    fun getTrainingProgramList(): LiveData<List<TrainingProgram>>
}