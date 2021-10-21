package com.example.melnyk_sportea_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import com.example.melnyk_sportea_app.model.TrainingProgram
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrainingProgramFragmentViewModel @Inject constructor(
     getTrainingProgramList: GetTrainingProgramListUseCase,
) : ViewModel() {

    var trainingProgramList: LiveData<List<TrainingProgram>> = getTrainingProgramList.execute()

}