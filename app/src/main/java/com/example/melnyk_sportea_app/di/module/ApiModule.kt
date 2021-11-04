package com.example.melnyk_sportea_app.di.module

import android.content.Context
import com.example.melnyk_sportea_app.BuildConfig
import com.example.melnyk_sportea_app.api.ApiService
import com.example.melnyk_sportea_app.api.InternetConnection
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.repository.QuotesRepository
import com.example.melnyk_sportea_app.repository.TrainingProgramsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(var context: Context) {
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
    fun provideRemoteDataSourceImpl(
        apiService: ApiService,
        databaseReference: DatabaseReference
    ): RemoteDataSourceImpl {
        return RemoteDataSourceImpl(apiService, databaseReference)
    }

    @Provides
    fun provideQuotesRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl,
        localDataSourceImpl: LocalDataSourceImpl,
    ): QuotesRepository {
        return QuotesRepository(remoteDataSourceImpl, localDataSourceImpl, context)
    }

    @Provides
    fun provideExerciseRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl,
        localDataSourceImpl: LocalDataSourceImpl,
        internetConnection: InternetConnection
    ): TrainingProgramsRepository {
        return TrainingProgramsRepository(
            remoteDataSourceImpl,
            localDataSourceImpl,
            context,
            internetConnection
        )
    }

    @Provides
    fun provideInternetConnection(): InternetConnection {
        return InternetConnection()
    }

    @Provides
    fun provideFirebaseAuth() : FirebaseAuth{
        return Firebase.auth
    }
}