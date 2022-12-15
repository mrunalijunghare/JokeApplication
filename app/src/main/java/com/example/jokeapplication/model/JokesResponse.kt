package com.example.jokeapplication.model

import com.google.gson.annotations.SerializedName
import com.example.jokeapplication.model.JokeClass
import java.util.ArrayList

class JokesResponse {
    @SerializedName("error")
    var error = false

    @SerializedName("amount")
    var amount = 0

    @JvmField
    @SerializedName("jokes")
    var arrayListJokes: ArrayList<JokeClass>? = null
    override fun toString(): String {
        return "JokesResponse{" +
                "error=" + error +
                ", amount=" + amount +
                ", jokes=" + arrayListJokes +
                '}'
    }
}