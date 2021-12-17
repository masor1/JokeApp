package com.masorone.jokeapp

import androidx.annotation.DrawableRes

class ViewModel(private val model: Model) {

    private var callback: DataCallback? = null

    fun init(callback: DataCallback) {
        this.callback = callback

        model.init(object : ResultCallback {
            override fun provideQuote(quote: Quote) {
                callback.let {
                    quote.map(it)
                }
            }
        })
    }

    fun getQuote() {
        model.getQuote()
    }

    fun clear() {
        callback = null
        model.clear()
    }

    fun chooseFavorites(checked: Boolean) {

    }
}

interface DataCallback {

    fun provideText(text: String)

    fun provideIconRes(@DrawableRes id: Int)
}