package com.masorone.jokeapp

class Quote(
    private val id: String,
    private val author: String,
    private val content: String,
    private val dateAdded: String
) {
    fun getJokeUI() = "+-----------------------------------------------------+\n" +
            "| id |  $id\n" +
            "+-----------------------------------------------------+\n" +
            "| author |  $author\n" +
            "+-----------------------------------------------------+\n" +
            "| content |  $content\n" +
            "+-----------------------------------------------------+\n" +
            "| dateAdded |  $dateAdded\n" +
    "+-----------------------------------------------------+"
}