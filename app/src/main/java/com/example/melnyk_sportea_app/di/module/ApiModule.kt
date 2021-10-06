package com.example.melnyk_sportea_app.di.module

import com.example.melnyk_sportea_app.BuildConfig
import com.example.melnyk_sportea_app.api.ApiService
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.repository.TrainingProgramsRepository
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.QUOTES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiServiceImpl(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService,
        databaseReference: DatabaseReference
    ): RemoteDataSourceImpl {
        return RemoteDataSourceImpl(apiService, databaseReference)
    }

    @Provides
    fun provideExerciseRepository(remoteDataSourceImpl: RemoteDataSourceImpl) : TrainingProgramsRepository{
        return TrainingProgramsRepository(remoteDataSourceImpl)
    }
}