package com.example.melnyk_sportea_app.repository

import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.Quote

class QuotesRepository(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl
) {
    suspend fun getQuotesListFromApi(): List<Quote> {
        return remoteDataSourceImpl.getQuotes()
    }

    fun getQuotesListFromDB(): List<Quote> {
        return localDataSourceImpl.getQuotes()
    }

    suspend fun addQuote(quote: Quote) {
        localDataSourceImpl.addQuote(quote)
    }

    suspend fun removeQuote(quote: Quote) {
        localDataSourceImpl.removeQuote(quote)
    }
}