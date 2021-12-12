package com.masorone.jokeapp

import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException

/**
 * {"_id": "QdK00IhCNX", "теги": ["известные-цитаты", "вдохновляющие"], "author": "Ларри Пейдж", "content": "Если вы меняете мир, вы работаете над важными делами. Вам не терпится встать утром. "," authorSlug ":" larry-page "," length ": 106," dateAdded ":" 2021-06-18 "," dateModified ": «2021-06-18»}
 */

interface JokeService {
    fun getJoke(callback: ServiceCallback)

    class Base() : JokeService {
        override fun getJoke(callback: ServiceCallback) {
            Thread {
                var connection: HttpURLConnection? = null
                try {
                    val url = URL(JOKE_URL)
                    connection = url.openConnection() as HttpURLConnection
                    InputStreamReader(BufferedInputStream(connection.inputStream)).use {
                        val line: String = it.readText()
                        callback.success(line)
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
    fun success(data: String)
    fun error(type: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    OTHER
}