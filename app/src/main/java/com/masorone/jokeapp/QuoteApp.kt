package com.masorone.jokeapp

import android.app.Application
import com.google.gson.Gson

class QuoteApp : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(
            Model.Base(
                QuoteService.Base(Gson()),
                ResourceManager.ResString(this)
            )
        )
    }
}
