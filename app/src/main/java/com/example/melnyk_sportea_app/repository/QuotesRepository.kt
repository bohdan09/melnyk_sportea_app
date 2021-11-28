package com.example.melnyk_sportea_app.repository

import com.example.melnyk_sportea_app.data.source.LocalDataSource
import com.example.melnyk_sportea_app.data.source.RemoteDataSource
import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.wrapper.Quotes
import io.reactivex.Observable

class QuotesRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
) {

    fun getQuotesListFromApi(): Observable<Quotes> {
        return remoteDataSource.getQuotes()
    }

    fun getQuotesListFromDB(): List<Quote> {
        return localDataSource.getQuotes()
    }

    suspend fun addQuote(quote: Quote) {
        localDataSource.addQuote(quote)
    }

    suspend fun removeQuote(quote: Quote) {
        localDataSource.removeQuote(quote)
    }

}