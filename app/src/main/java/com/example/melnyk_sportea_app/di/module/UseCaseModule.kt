package com.example.melnyk_sportea_app.di.module

import android.app.Application
import com.example.melnyk_sportea_app.api.InternetConnection
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.domain.usecase.CacheTrainingProgramToDbUseCase
import com.example.melnyk_sportea_app.domain.usecase.GetTrainingProgramListUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule(val application: Application) {
    @Provides
    fun provideGetTrainingProgramUseCase(
        remoteDataSourceImpl: RemoteDataSourceImpl,
        localDataSourceImpl: LocalDataSourceImpl,
        internetConnection: InternetConnection
    ): GetTrainingProgramListUseCase {
        return GetTrainingProgramListUseCase(
            remoteDataSourceImpl,
            localDataSourceImpl,
            application,
            internetConnection
        )
    }

    @Provides
    fun provideCacheTrainingProgramToDbUseCase(
        localDataSourceImpl: LocalDataSourceImpl
    ): CacheTrainingProgramToDbUseCase {
        return CacheTrainingProgramToDbUseCase(localDataSourceImpl)
    }

}