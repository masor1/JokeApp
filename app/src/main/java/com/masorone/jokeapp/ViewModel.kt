package com.masorone.jokeapp

class ViewModel(private val model: Model) {

    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback

        model.init(object : ResultCallback {
            override fun provideSuccess(data: Quote) = callback.provideText(data.getJokeUI())
            override fun provideError(error: QuoteFailure) = callback.provideText(error.getMessage())
        })
    }

    fun getQuote() {
        model.getQuote()
    }

    fun clear() {
        callback = null
        model.clear()
    }
}

interface TextCallback {
    fun provideText(text: String)
}