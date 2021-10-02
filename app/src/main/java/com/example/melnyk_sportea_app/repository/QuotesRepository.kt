package com.example.melnyk_sportea_app.repository

import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.Quote

class QuotesRepository(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) {
    suspend fun getQuotesList(): List<Quote>{
        return remoteDataSourceImpl.getQuotes()
    }
}