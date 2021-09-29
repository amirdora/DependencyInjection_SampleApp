package com.amirdora.dependencyinjection.data.db

import androidx.lifecycle.LiveData
import com.amirdora.dependencyinjection.data.model.Quote

interface QuoteDao {
    fun addQuote(quote: Quote)
    fun getQuotes(): LiveData<List<Quote>>
}