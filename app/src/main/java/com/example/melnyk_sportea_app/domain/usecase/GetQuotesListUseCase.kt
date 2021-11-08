package com.example.melnyk_sportea_app.domain.usecase

import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.wrapper.Quotes
import io.reactivex.Observable

class GetQuotesListUseCase(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) {
    fun execute(): Observable<Quotes> {
        return remoteDataSourceImpl.getQuotes()
    }

}