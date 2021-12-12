package com.masorone.jokeapp

class Quote(
    private val id: String,
    private val author: String,
    private val content: String,
    private val dateAdded: String
) {
    fun getJokeUI() = "$id\n$author\n$content\n$dateAdded"
}