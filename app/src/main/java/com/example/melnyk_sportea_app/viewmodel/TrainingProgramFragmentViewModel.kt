package com.example.melnyk_sportea_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.melnyk_sportea_app.domain.usecase.CacheTrainingProgramUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import com.example.melnyk_sportea_app.model.TrainingProgram
import javax.inject.Inject

class TrainingProgramFragmentViewModel @Inject constructor(
    private val getTrainingProgramList: GetTrainingProgramListUseCase,
    private val cacheTrainingProgramToDbUseCase: CacheTrainingProgramUseCase
) : ViewModel() {


    var trainingProgramList: LiveData<List<TrainingProgram>> = getTrainingProgramList.execute()


    suspend fun cash(list: List<TrainingProgram>) {
        Log.d("TAG", "ViewModel cache")
        cacheTrainingProgramToDbUseCase.execute(list)
    }

}