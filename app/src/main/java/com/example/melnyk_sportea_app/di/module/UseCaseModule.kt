package com.example.melnyk_sportea_app.di.module

import android.app.Application
import com.example.melnyk_sportea_app.domain.usecase.*
import com.example.melnyk_sportea_app.repository.TrainingJournalRepository
import com.example.melnyk_sportea_app.repository.TrainingProgramsRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule(val application: Application) {
    @Provides
    fun provideGetTrainingProgramUseCase(
        trainingProgramsRepository: TrainingProgramsRepository
    ): GetTrainingProgramListUseCase {
        return GetTrainingProgramListUseCase(trainingProgramsRepository)
    }

    @Provides
    fun provideGetTrainingJournalUseCase(
        trainingJournalRepository: TrainingJournalRepository
    ): GetTrainingJournalLiveDataUseCase {
        return GetTrainingJournalLiveDataUseCase(trainingJournalRepository)
    }

    @Provides
    fun provideAddTrainingUseCase(
        trainingJournalRepository: TrainingJournalRepository
    ): AddTrainingUseCase {
        return AddTrainingUseCase(trainingJournalRepository)
    }

    @Provides
    fun provideClearTrainingJournalUseCase(
        trainingJournalRepository: TrainingJournalRepository
    ): ClearTrainingJournalUseCase {
        return ClearTrainingJournalUseCase(trainingJournalRepository)
    }

    @Provides
    fun provideCacheTrainingProgramsUseCase(
        trainingProgramsRepository: TrainingProgramsRepository
    ) : CacheTrainingProgramsUseCase{
        return CacheTrainingProgramsUseCase(trainingProgramsRepository)
    }
}