package com.amirdora.dependencyinjection.ui.quotes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.amirdora.dependencyinjection.R
import com.amirdora.dependencyinjection.data.model.Quote
import com.amirdora.dependencyinjection.databinding.ActivityQuotesBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class QuotesActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: QuotesViewModelFactory by instance()
    private var binding: ActivityQuotesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        binding = ActivityQuotesBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        initializeUi()
    }

    private fun initializeUi() {
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(QuotesViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getQuotes().observe(this, { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            binding!!.textViewQuotes.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        binding!!.buttonAddQuote.setOnClickListener {
            val quote = Quote(
                binding!!.editTextQuote.text.toString(),
                binding!!.editTextAuthor.text.toString()
            )
            viewModel.addQuote(quote)
            binding!!.editTextQuote.setText("")
            binding!!.editTextAuthor.setText("")
        }
    }
}