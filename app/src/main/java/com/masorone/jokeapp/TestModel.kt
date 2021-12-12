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
                0 -> callback?.provideSuccess(Quote("testText", "testPunchline", "", ""))
                1 -> callback?.provideError(noConnection)
                2 -> callback?.provideError(serviceUnavailable)
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