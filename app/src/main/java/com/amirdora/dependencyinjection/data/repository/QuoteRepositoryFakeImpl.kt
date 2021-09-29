package com.amirdora.dependencyinjection.data.repository

import androidx.lifecycle.LiveData
import com.amirdora.dependencyinjection.data.db.QuoteDao
import com.amirdora.dependencyinjection.data.model.Quote

class QuoteRepositoryFakeImpl(private val quoteDao : QuoteDao) : QuoteRepository {
    override fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    override fun getQuotes() = quoteDao.getQuotes()
}