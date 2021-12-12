package com.masorone.jokeapp

import android.app.Application

class QuoteApp : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(
            Model.Base(
                QuoteService.Base(),
                ResourceManager.ResString(this)
            )
        )
    }
}
