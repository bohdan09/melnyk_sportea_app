package com.example.melnyk_sportea_app.di.module

import android.content.Context
import com.example.melnyk_sportea_app.BuildConfig
import com.example.melnyk_sportea_app.api.ApiService
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.repository.QuotesRepository
import com.google.firebase.database.DatabaseReference
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

}