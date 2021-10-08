package com.example.melnyk_sportea_app.di.module

import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import com.example.melnyk_sportea_app.viewmodel.TrainingProgramFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideTrainingProgramFragmentViewModel(
        getTrainingProgramListUseCase: GetTrainingProgramListUseCase
    ): TrainingProgramFragmentViewModel {
        return TrainingProgramFragmentViewModel(getTrainingProgramListUseCase)
    }
}