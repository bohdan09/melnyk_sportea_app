package com.example.melnyk_sportea_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import com.example.melnyk_sportea_app.model.TrainingProgram

class TrainingProgramFragmentViewModel(
    getTrainingProgramList: GetTrainingProgramListUseCase
) : ViewModel() {

    var trainingProgramList: LiveData<List<TrainingProgram>> = getTrainingProgramList.execute()

}