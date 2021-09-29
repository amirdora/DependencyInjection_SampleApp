package com.amirdora.dependencyinjection

import android.app.Application
import com.amirdora.dependencyinjection.data.db.Database
import com.amirdora.dependencyinjection.data.db.DatabaseFakeImpl
import com.amirdora.dependencyinjection.data.db.QuoteDao
import com.amirdora.dependencyinjection.data.repository.QuoteRepository
import com.amirdora.dependencyinjection.data.repository.QuoteRepositoryFakeImpl
import com.amirdora.dependencyinjection.ui.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class QuotesApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<Database>() with singleton { DatabaseFakeImpl() }
        bind<QuoteDao>() with singleton { instance<Database>().quoteDao }
        bind<QuoteRepository>() with singleton { QuoteRepositoryFakeImpl(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
    }
}