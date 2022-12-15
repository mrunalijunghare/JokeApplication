package com.example.jokeapplication.repository

import android.util.Log
import com.example.jokeapplication.network.JokesApi
import com.example.jokeapplication.retrofit.RetrofitClient
import androidx.lifecycle.LiveData
import com.example.jokeapplication.model.JokesResponse
import androidx.lifecycle.MutableLiveData
import com.example.jokeapplication.model.JokeClass
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
        jokesApi?.getJokes(category, jokeType, amount)!!.enqueue(object : Callback<JokesResponse?> {
            override fun onResponse(
                call: Call<JokesResponse?>,
                response: Response<JokesResponse?>
            ) {
                if (response.body() != null) {
                    data.value = response.body()
                    Log.d(TAG, "jokes response: $response")
                    Log.d(TAG, "jokes response: " + response.body().toString())
                }
            }

            override fun onFailure(call: Call<JokesResponse?>, t: Throwable) {
                data.value = null
                Log.d(TAG, " jokes response: null")
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
        jokesApi?.getSingleJoke(category, jokeType, amount)!!.enqueue(object : Callback<JokeClass?> {
            override fun onResponse(call: Call<JokeClass?>, response: Response<JokeClass?>) {
                if (response.body() != null) {
                    data.value = response.body()
                    Log.d(TAG, "joke response: $response")
                    Log.d(TAG, "joke response body: " + response.body().toString())
                }
            }

            override fun onFailure(call: Call<JokeClass?>, t: Throwable) {
                data.value = null
                Log.d(TAG, " jokes response: null")
            }
        })
        return data
    }

    companion object {
        private val TAG = JokesRepository::class.java.simpleName
    }
}