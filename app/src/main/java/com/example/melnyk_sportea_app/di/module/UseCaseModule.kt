package com.example.melnyk_sportea_app.di.module

import android.app.Application
import com.example.melnyk_sportea_app.api.InternetConnection
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import com.example.melnyk_sportea_app.shared.preferences.PreferencesClientImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule(val application: Application) {
    @Provides
    fun provideGetTrainingProgramUseCase(
        remoteDataSourceImpl: RemoteDataSourceImpl,
        preferencesClientImpl: PreferencesClientImpl,
        internetConnection: InternetConnection
    ): GetTrainingProgramListUseCase {
        return GetTrainingProgramListUseCase(
            remoteDataSourceImpl,
            preferencesClientImpl,
            application,
            internetConnection
        )
    }
}