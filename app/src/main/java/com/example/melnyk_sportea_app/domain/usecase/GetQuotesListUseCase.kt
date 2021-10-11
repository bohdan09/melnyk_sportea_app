package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.Quote

class GetQuotesListUseCase(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) {
    suspend fun execute(): List<Quote> {
        return remoteDataSourceImpl.getQuotes()
    }

}