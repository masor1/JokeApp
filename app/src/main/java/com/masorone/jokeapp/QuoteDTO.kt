package com.masorone.jokeapp

import com.google.gson.annotations.SerializedName

/**
{"_id":"QdK00IhCNX","tags":["famous-quotes","inspirational"],"author":"Larry Page","content":"If you're changing the world, you're working on important things. You're excited to get up in the morning.","authorSlug":"larry-page","length":106,"dateAdded":"2021-06-18","dateModified":"2021-06-18"}
 */

class QuoteDTO(
    @SerializedName("_id")
    private val id: String,
    @SerializedName("author")
    private val author: String,
    @SerializedName("content")
    private val content: String,
    @SerializedName("dateAdded")
    private val dateAdded: String
) {
    fun toQuote() = Quote.Base(id, author, content, dateAdded)
}