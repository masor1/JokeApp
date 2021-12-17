package com.masorone.jokeapp

import androidx.annotation.DrawableRes

abstract class Quote(
    private val id: String,
    private val author: String,
    private val content: String,
    private val dateAdded: String
) {
    fun getJokeUI() = "$id\n$author\n$content\n"


    fun map(callback: DataCallback) = callback.run {
        provideText(getJokeUI())
        provideIconRes(getIconResId())
    }

    @DrawableRes
    abstract fun getIconResId(): Int

    class Base(
        id: String,
        author: String,
        content: String,
        dateAdded: String
    ) : Quote(
        id,
        author,
        content,
        dateAdded
    ) {
        override fun getIconResId() = R.drawable.baseline_favorite_border_24
    }

    class Favorite(
        id: String,
        author: String,
        content: String,
        dateAdded: String
    ) : Quote(
        id,
        author,
        content,
        dateAdded
    ) {
        override fun getIconResId() = R.drawable.baseline_favorite_24
    }

    class Failed(
        id: String = "",
        author: String = "",
        content: String,
        dateAdded: String = ""
    ) : Quote(
        id,
        author,
        content,
        author
    ) {
        override fun getIconResId() = 0
    }
}