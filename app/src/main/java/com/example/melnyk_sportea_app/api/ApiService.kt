package com.example.melnyk_sportea_app.api

import com.example.melnyk_sportea_app.model.Quote
import retrofit2.http.GET

interface ApiService {
    @GET("quotes")
    suspend fun getQuotesList(): List<Quote>
}