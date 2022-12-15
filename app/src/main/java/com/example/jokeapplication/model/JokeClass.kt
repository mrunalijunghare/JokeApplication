package com.example.jokeapplication.model

import com.google.gson.annotations.SerializedName

class JokeClass {
    @SerializedName("error")
    var error: String? = null

    @SerializedName("category")
    var category: String? = null

    @SerializedName("type")
    var type: String? = null

    @JvmField
    @SerializedName("setup")
    var setup: String? = null

    @JvmField
    @SerializedName("delivery")
    var delivery: String? = null

    @SerializedName("flags")
    var flags: Flags? = null

    @SerializedName("safe")
    var safe = false

    @SerializedName("id")
    var id = 0

    @SerializedName("lang")
    var lang: String? = null

    @JvmField
    @SerializedName("joke")
    var joke: String? = null
    override fun toString(): String {
        return "Joke{" +
                "error='" + error + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", setup='" + setup + '\'' +
                ", delivery='" + delivery + '\'' +
                ", flags=" + flags +
                ", safe=" + safe +
                ", id=" + id +
                ", lang='" + lang + '\'' +
                ", joke='" + joke + '\'' +
                '}'
    }
}