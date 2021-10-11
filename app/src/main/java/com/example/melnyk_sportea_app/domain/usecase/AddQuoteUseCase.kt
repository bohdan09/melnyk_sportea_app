package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.model.Quote

class AddQuoteUseCase(
    private val localDataSourceImpl: LocalDataSourceImpl
) {
    suspend fun execute(quote: Quote) {
        localDataSourceImpl.addQuote(quote)
    }
}