package com.example.melnyk_sportea_app.api

import com.example.melnyk_sportea_app.model.wrapper.Quotes
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("api/quotes")
    fun getQuotesList(): Observable<Quotes>
}