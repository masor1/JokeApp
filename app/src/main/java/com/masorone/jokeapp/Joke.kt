package com.masorone.jokeapp

class Joke(private val text: String, private val punchline: String) {

    fun getJokeUI() = "$text\n$punchline"
}