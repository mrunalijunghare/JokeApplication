package com.example.jokeapplication.model

import java.io.Serializable

class JokesRequest : Serializable {
    var category: String = Util.ANY
    var jokeType: String? = null
    var amount: String? = null

    init {
        category = Util.ANY
    }

    override fun toString(): String {
        return "JokesRequest{" +
                "category=" + category +
                ", jokeType='" + jokeType + '\'' +
                ", amount=" + amount +
                '}'
    }
}
