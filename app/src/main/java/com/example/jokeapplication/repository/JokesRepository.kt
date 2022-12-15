package com.example.jokeapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jokeapplication.model.JokeClass
import com.example.jokeapplication.model.JokesResponse
import com.example.jokeapplication.network.JokesApi
import com.example.jokeapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokesRepository {
    private val jokesApi: JokesApi? = RetrofitClient.retrofitInstance?.create(JokesApi::class.java)

    fun getJokesList(
        category: String?,
        jokeType: String?,
        amount: String?
    ): LiveData<JokesResponse?> {
        val data = MutableLiveData<JokesResponse?>()
        jokesApi?.getJokes(category, jokeType, amount)?.enqueue(object : Callback<JokesResponse?> {
            override fun onResponse(
                call: Call<JokesResponse?>,
                response: Response<JokesResponse?>
            ) {
                if (response.body() != null) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<JokesResponse?>, t: Throwable) {
                data.value = null
             }
        })
        return data
    }

    fun getSingleJokeList(
        category: String?,
        jokeType: String?,
        amount: String?
    ): LiveData<JokeClass?> {
        val data = MutableLiveData<JokeClass?>()
        jokesApi?.getSingleJoke(category, jokeType, amount)?.enqueue(object : Callback<JokeClass?> {
            override fun onResponse(call: Call<JokeClass?>, response: Response<JokeClass?>) {
                if (response.body() != null) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<JokeClass?>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

}