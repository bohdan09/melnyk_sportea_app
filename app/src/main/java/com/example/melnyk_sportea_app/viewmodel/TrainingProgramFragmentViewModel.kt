package com.example.melnyk_sportea_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melnyk_sportea_app.domain.usecase.CacheTrainingProgramsUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import com.example.melnyk_sportea_app.model.TrainingProgram
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrainingProgramFragmentViewModel @Inject constructor(
    getTrainingProgramList: GetTrainingProgramListUseCase,
    private val cacheTrainingPrograms: CacheTrainingProgramsUseCase
) : ViewModel() {

    var trainingProgramList: LiveData<List<TrainingProgram>> = getTrainingProgramList.execute()

    fun cacheTrainingPrograms(programs: List<TrainingProgram>) {
        viewModelScope.launch(Dispatchers.IO) {
            cacheTrainingPrograms.execute(programs)
        }
    }
}