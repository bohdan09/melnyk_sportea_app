package com.example.melnyk_sportea_app.di.module

import android.app.Application
import com.example.melnyk_sportea_app.api.InternetConnection
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.domain.usecase.CacheTrainingProgramToDbUseCase
import com.example.melnyk_sportea_app.domain.usecase.CacheTrainingProgramUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import com.example.melnyk_sportea_app.repository.TrainingProgramsRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule(val application: Application) {
    @Provides
    fun provideGetTrainingProgramUseCase(
        trainingProgramsRepository: TrainingProgramsRepository
    ): GetTrainingProgramListUseCase {
        return GetTrainingProgramListUseCase(
           trainingProgramsRepository
        )
    }

    @Provides
    fun provideCacheTrainingProgramUseCase(
        trainingProgramsRepository: TrainingProgramsRepository
    ): CacheTrainingProgramUseCase {
        return CacheTrainingProgramUseCase(trainingProgramsRepository)
    }

}