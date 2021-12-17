package com.masorone.jokeapp

class TestModel(resourceManager: ResourceManager) : Model {

    private var callback: ResultCallback? = null
    private var count = 0
    private val noConnection = QuoteFailure.NoConnection(resourceManager)
    private val serviceUnavailable = QuoteFailure.ServiceUnavailable(resourceManager)

    override fun getQuote() {
        Thread {
            Thread.sleep(2000)
            when (count) {
                0 -> callback?.provideQuote(Quote.Base("testText", "testPunchline", "", ""))
                1 -> callback?.provideQuote(Quote.Favorite("1", "favourite", "favourite", "favourite"))
                2 -> callback?.provideQuote(Quote.Failed(content = serviceUnavailable.getMessage()))
            }
            count++
            if (count == 3) count = 0
        }.start()
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
        count = 0
    }
}