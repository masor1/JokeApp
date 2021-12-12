package com.masorone.jokeapp

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes stringResId: Int): String


    class ResString(private val context: Context): ResourceManager {
        override fun getString(stringResId: Int) = context.getString(stringResId)
    }
}