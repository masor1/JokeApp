package com.masorone.jokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var quoteLoadingProgressBar: ProgressBar
    private lateinit var quoteOrErrorTextView: TextView
    private lateinit var getQuoteButton: Button
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app()
    }

    private fun app() {
        initViews()
        initViewModel()
        setFunctional()
    }

    private fun initViewModel() {
        viewModel = (application as QuoteApp).viewModel
    }

    private fun initViews() {
        quoteLoadingProgressBar = findViewById(R.id.quoteLoading)
        quoteOrErrorTextView = findViewById(R.id.quoteOrError)
        getQuoteButton = findViewById(R.id.getQuote)
    }

    private fun setFunctional() {
        quoteLoadingProgressBar.visibility = View.INVISIBLE
        getQuoteButton.setOnClickListener {
            getQuote()
        }
        viewModel.init(createTextCallback())
    }

    private fun getQuote() {
        getQuoteButton.isEnabled = false
        quoteLoadingProgressBar.visibility = View.VISIBLE
        viewModel.getQuote()
    }

    private fun createTextCallback() = object : TextCallback {
        override fun provideText(text: String) = runOnUiThread {
            getQuoteButton.isEnabled = true
            quoteLoadingProgressBar.visibility = View.INVISIBLE
            quoteOrErrorTextView.text = text
        }
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}