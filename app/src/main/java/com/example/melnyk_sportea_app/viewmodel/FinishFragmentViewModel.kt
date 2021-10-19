package com.example.melnyk_sportea_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melnyk_sportea_app.domain.usecase.AddTrainingUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingJournalLiveDataUseCase
import com.example.melnyk_sportea_app.model.TrainingJournal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FinishFragmentViewModel @Inject constructor(
    getTrainingJournal: GetTrainingJournalLiveDataUseCase,
    private val addTrainingUseCase: AddTrainingUseCase
) : ViewModel() {
    val trainingJournal: LiveData<List<TrainingJournal>> = getTrainingJournal.execute()

    fun addTraining(training: TrainingJournal){
        viewModelScope.launch(Dispatchers.IO){
            addTrainingUseCase.execute(training)
        }
    }
}