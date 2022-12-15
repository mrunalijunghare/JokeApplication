package com.example.jokeapplication.model

object Util {
    const val ANY = "Any"
    private const val KEY = "KEY"
    @JvmField
    val categoryStrArray = arrayOf("Programming", "Misc", "Dark", "Pun", "Spooky", "Christmas")
    @JvmField
    val KEY_JOKE_REQUEST = KEY + JokesRequest::class.java.name

}