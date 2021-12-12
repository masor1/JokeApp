package com.masorone.jokeapp

interface QuoteFailure {
    fun getMessage(): String

    class NoConnection(private val resourceManager: ResourceManager) : QuoteFailure {
        override fun getMessage() = resourceManager.getString(R.string.no_connection)
    }

    class ServiceUnavailable(private val resourceManager: ResourceManager) : QuoteFailure {
        override fun getMessage() = resourceManager.getString(R.string.service_unavailable)
    }
}