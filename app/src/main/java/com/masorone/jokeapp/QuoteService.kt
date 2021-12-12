package com.masorone.jokeapp

import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException


interface QuoteService {
    fun getQuote(callback: ServiceCallback)

    class Base(private val gson: Gson) : QuoteService {
        override fun getQuote(callback: ServiceCallback) {
            Thread {
                var connection: HttpURLConnection? = null
                try {
                    val url = URL(JOKE_URL)
                    connection = url.openConnection() as HttpURLConnection
                    InputStreamReader(BufferedInputStream(connection.inputStream)).use {
                        val line: String = it.readText()
                        val quoteDTO = gson.fromJson(line, QuoteDTO::class.java)
                        callback.success(quoteDTO)
                    }
                } catch (e: Exception) {
                    if (e is UnknownHostException)
                        callback.error(ErrorType.NO_CONNECTION)
                    else
                        callback.error(ErrorType.OTHER)
                } finally {
                    connection?.disconnect()
                }
            }.start()
        }
    }

    private companion object {
        const val JOKE_URL = "https://api.quotable.io/random"
    }
}

interface ServiceCallback {
    fun success(data: QuoteDTO)
    fun error(type: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    OTHER
}