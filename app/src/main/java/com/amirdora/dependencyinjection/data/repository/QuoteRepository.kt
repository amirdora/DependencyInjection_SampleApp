package com.amirdora.dependencyinjection.data.repository

import androidx.lifecycle.LiveData
import com.amirdora.dependencyinjection.data.model.Quote

interface QuoteRepository {
    fun addQuote(quote: Quote)
    fun getQuotes(): LiveData<List<Quote>>
}