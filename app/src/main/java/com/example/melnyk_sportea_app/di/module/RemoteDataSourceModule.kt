package com.example.melnyk_sportea_app.di.module

import com.example.melnyk_sportea_app.api.ApiService
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides

@Module
class RemoteDataSourceModule {
    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService,
        databaseReference: DatabaseReference
    ): RemoteDataSourceImpl {
        return RemoteDataSourceImpl(apiService, databaseReference)
    }
}