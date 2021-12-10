package com.masorone.jokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var jokeLoadingProgressBar: ProgressBar
    private lateinit var jokeOrErrorTextView: TextView
    private lateinit var getJokeButton: Button
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
        viewModel = (application as JokeApp).viewModel
    }

    private fun initViews() {
        jokeLoadingProgressBar = findViewById(R.id.jokeLoading)
        jokeOrErrorTextView = findViewById(R.id.jokeOrError)
        getJokeButton = findViewById(R.id.getJoke)
    }

    private fun setFunctional() {
        jokeLoadingProgressBar.visibility = View.INVISIBLE
        getJokeButton.setOnClickListener {
            getJoke()
        }
        viewModel.init(createTextCallback())
    }

    private fun getJoke() {
        getJokeButton.isEnabled = false
        jokeLoadingProgressBar.visibility = View.VISIBLE
        viewModel.getJoke()
    }

    private fun createTextCallback() = object : TextCallback {
        override fun provideText(text: String) = runOnUiThread {
            getJokeButton.isEnabled = true
            jokeLoadingProgressBar.visibility = View.INVISIBLE
            jokeOrErrorTextView.text = text
        }
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}