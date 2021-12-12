package com.masorone.jokeapp

class TestModel(resourceManager: ResourceManager) : Model {

    private var callback: ResultCallback? = null
    private var count = 0
    private val noConnection = JokeFailure.NoConnection(resourceManager)
    private val serviceUnavailable = JokeFailure.ServiceUnavailable(resourceManager)

    override fun getJoke() {
        Thread {
            Thread.sleep(2000)
            when (count) {
                0 -> callback?.provideSuccess(Joke("testText", "testPunchline"))
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