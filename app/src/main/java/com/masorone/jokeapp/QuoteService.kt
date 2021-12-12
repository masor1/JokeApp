package com.masorone.jokeapp

import retrofit2.Call
import retrofit2.http.GET

interface QuoteService {

    @GET(QUOTE_URL)
    fun getQuote(): Call<QuoteDTO>

//    fun getQuote(callback: ServiceCallback)
//
//    class Base(private val gson: Gson) : QuoteService {
//        override fun getQuote(callback: ServiceCallback) {
//            Thread {
//                var connection: HttpURLConnection? = null
//                try {
//                    val url = URL(JOKE_URL)
//                    connection = url.openConnection() as HttpURLConnection
//                    InputStreamReader(BufferedInputStream(connection.inputStream)).use {
//                        val line: String = it.readText()
//                        val quoteDTO = gson.fromJson(line, QuoteDTO::class.java)
//                        callback.success(quoteDTO)
//                    }
//                } catch (e: Exception) {
//                    if (e is UnknownHostException)
//                        callback.error(ErrorType.NO_CONNECTION)
//                    else
//                        callback.error(ErrorType.OTHER)
//                } finally {
//                    connection?.disconnect()
//                }
//            }.start()
//        }
//    }

    private companion object {
        const val QUOTE_URL = "https://api.quotable.io/random/"
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