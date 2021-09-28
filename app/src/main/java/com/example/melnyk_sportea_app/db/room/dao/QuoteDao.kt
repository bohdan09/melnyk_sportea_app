package com.example.melnyk_sportea_app.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.melnyk_sportea_app.db.room.Constants.QUOTE_TABLE_NAME
import com.example.melnyk_sportea_app.model.Quote

@Dao
interface QuoteDao {
    @Insert
    suspend fun addQuote(quote: Quote)

    @Query("SELECT * FROM $QUOTE_TABLE_NAME")
    fun getQuote() : List<Quote>
}