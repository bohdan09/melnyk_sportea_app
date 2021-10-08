package com.example.melnyk_sportea_app.di.module

import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetTrainingProgramUseCase(remoteDataSourceImpl: RemoteDataSourceImpl): GetTrainingProgramListUseCase {
        return GetTrainingProgramListUseCase(remoteDataSourceImpl)
    }
}