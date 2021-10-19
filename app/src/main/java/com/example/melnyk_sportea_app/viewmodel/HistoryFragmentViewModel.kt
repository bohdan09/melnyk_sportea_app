package com.example.melnyk_sportea_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingJournalLiveDataUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.model.TrainingProgram
import javax.inject.Inject

class HistoryFragmentViewModel @Inject constructor(
    getTrainingProgramListUseCase: GetTrainingProgramListUseCase,
    getTrainingJournalLiveDataUseCase: GetTrainingJournalLiveDataUseCase
) : ViewModel() {

    val trainingJournal: LiveData<List<TrainingJournal>> =
        getTrainingJournalLiveDataUseCase.execute()

    val trainingProgram: LiveData<List<TrainingProgram>> = getTrainingProgramListUseCase.execute()
}