package com.example.melnyk_sportea_app.api

import com.example.melnyk_sportea_app.api.model.QuotesData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("quotes")
    fun getQuotesList(): Call<QuotesData>
}