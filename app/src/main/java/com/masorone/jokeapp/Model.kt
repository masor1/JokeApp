package com.masorone.jokeapp

interface Model {

    fun getQuote()

    fun init(callback: ResultCallback)

    fun clear()

    class Base(
        private val service: QuoteService,
        private val resourceManager: ResourceManager
    ) : Model {
        private var callback: ResultCallback? = null

        override fun getQuote() {
            service.getQuote(object : ServiceCallback {
                override fun success(data: QuoteDTO) {
                    callback?.provideSuccess(data.toQuote())
                }

                override fun error(type: ErrorType) {
                    when (type) {
                        ErrorType.NO_CONNECTION -> callback?.provideError(QuoteFailure.NoConnection(resourceManager))
                        ErrorType.OTHER -> callback?.provideError(QuoteFailure.ServiceUnavailable(resourceManager))
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

    fun provideSuccess(data: Quote)

    fun provideError(error: QuoteFailure)
}