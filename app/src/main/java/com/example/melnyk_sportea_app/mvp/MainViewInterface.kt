package com.example.melnyk_sportea_app.mvp

import com.example.melnyk_sportea_app.model.wrapper.Quotes

interface MainViewInterface {
    fun displayQuotes(quotes: Quotes)
    fun displayError(s: String?)
}