package com.example.melnyk_sportea_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melnyk_sportea_app.domain.usecase.ClearTrainingJournalUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingJournalLiveDataUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramByIdLiveDataUseCase
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.model.TrainingProgram
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryFragmentViewModel @Inject constructor(
    getTrainingJournalLiveDataUseCase: GetTrainingJournalLiveDataUseCase,
    private val clearTrainingJournalUseCase: ClearTrainingJournalUseCase,
    private val getTrainingProgramByIdLiveDataUseCase: GetTrainingProgramByIdLiveDataUseCase
) : ViewModel() {

    var trainingJournal: LiveData<List<TrainingJournal>> =
        getTrainingJournalLiveDataUseCase.execute()

    lateinit var trainingProgramById: LiveData<TrainingProgram>

    fun clearHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            clearTrainingJournalUseCase.execute()
        }
    }

    fun getTrainingProgramById(id: Int) {
        trainingProgramById = getTrainingProgramByIdLiveDataUseCase.execute(id)
    }

}