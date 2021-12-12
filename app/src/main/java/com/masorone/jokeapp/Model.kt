package com.masorone.jokeapp

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

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
            service.getQuote().enqueue( object : retrofit2.Callback<QuoteDTO> {
                override fun onResponse(call: Call<QuoteDTO>, response: Response<QuoteDTO>) {
                    if (response.isSuccessful) {
                        callback?.provideSuccess(response.body()!!.toQuote())
                    } else {
                        callback?.provideError(QuoteFailure.ServiceUnavailable(resourceManager))
                    }
                }

                override fun onFailure(call: Call<QuoteDTO>, t: Throwable) {
                    if (t is UnknownHostException) {
                        callback?.provideError(QuoteFailure.NoConnection(resourceManager))
                    } else {
                        callback?.provideError(QuoteFailure.ServiceUnavailable(resourceManager))
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