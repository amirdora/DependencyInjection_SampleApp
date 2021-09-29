package com.amirdora.dependencyinjection.ui.quotes

import androidx.lifecycle.ViewModel
import com.amirdora.dependencyinjection.data.model.Quote
import com.amirdora.dependencyinjection.data.repository.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)

    fun getQuotes() = quoteRepository.getQuotes()
}