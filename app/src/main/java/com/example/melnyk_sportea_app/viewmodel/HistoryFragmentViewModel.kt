package com.example.melnyk_sportea_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melnyk_sportea_app.domain.usecase.ClearTrainingJournalUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingJournalLiveDataUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.model.TrainingProgram
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryFragmentViewModel @Inject constructor(
    getTrainingJournalLiveDataUseCase: GetTrainingJournalLiveDataUseCase,
    private val clearTrainingJournalUseCase: ClearTrainingJournalUseCase
) : ViewModel() {

    var trainingJournal: LiveData<List<TrainingJournal>> =
        getTrainingJournalLiveDataUseCase.execute()


    fun clearHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            clearTrainingJournalUseCase.execute()
        }
    }
}