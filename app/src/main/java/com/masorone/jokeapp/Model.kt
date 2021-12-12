package com.masorone.jokeapp

interface Model {

    fun getJoke()

    fun init(callback: ResultCallback)

    fun clear()

    class Base(
        private val service: JokeService,
        private val resourceManager: ResourceManager
    ) : Model {
        private var callback: ResultCallback? = null

        override fun getJoke() {
            service.getJoke(object : ServiceCallback {
                override fun success(data: String) {
                    callback?.provideSuccess(Joke(data, ""))
                }

                override fun error(type: ErrorType) {
                    when (type) {
                        ErrorType.NO_CONNECTION -> callback?.provideError(JokeFailure.NoConnection(resourceManager))
                        ErrorType.OTHER -> callback?.provideError(JokeFailure.ServiceUnavailable(resourceManager))
                    }
                }
            })
        }

        override fun init(callback: ResultCallback) {
            this.callback = callback
        }

        override fun clear() {
            callback = null
        }

    }
}

interface ResultCallback {

    fun provideSuccess(data: Joke)

    fun provideError(error: JokeFailure)
}