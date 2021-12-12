package com.masorone.jokeapp

class Quote(private val text: String, private val punchline: String) {

    fun getJokeUI() = "$text\n$punchline"
}