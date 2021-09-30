package com.example.melnyk_sportea_app.di.module

import com.example.melnyk_sportea_app.BuildConfig
import com.example.melnyk_sportea_app.api.ApiService
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

}