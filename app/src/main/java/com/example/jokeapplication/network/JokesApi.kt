package com.example.jokeapplication.network

import retrofit2.http.GET
import com.example.jokeapplication.model.JokesResponse
import com.example.jokeapplication.model.JokeClass
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface JokesApi {
    @GET("joke/{category}")
    fun getJokes(
        @Path("category") category: String?,
        @Query("type") jokeType: String?,
        @Query("amount") amount: String?
    ): Call<JokesResponse?>?

    @GET("joke/{category}")
    fun getSingleJoke(
        @Path("category") category: String?,
        @Query("type") jokeType: String?,
        @Query("amount") amount: String?
    ): Call<JokeClass?>?
}