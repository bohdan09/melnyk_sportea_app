package com.example.melnyk_sportea_app.api.model

class QuotesData : ArrayList<Quote>()
data class Quote(
    val text: String,
    val author: String
)