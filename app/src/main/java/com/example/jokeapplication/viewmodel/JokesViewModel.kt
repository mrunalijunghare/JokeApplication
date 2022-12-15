package com.example.jokeapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jokeapplication.model.Event
import com.example.jokeapplication.model.JokeClass
import com.example.jokeapplication.model.JokesRequest
import com.example.jokeapplication.model.JokesResponse
import com.example.jokeapplication.repository.JokesRepository

class JokesViewModel(application: Application) : AndroidViewModel(application) {
    private val jokesRepository: JokesRepository = JokesRepository()
    var jokesResponseLiveData: LiveData<JokesResponse?> = MutableLiveData()
        private set
    var singleJokeResponseLiveData: LiveData<JokeClass?> = MutableLiveData()
        private set
    private var jokesRequest: JokesRequest? = null

    fun setJokesRequest(jokesRequest: JokesRequest) {
        this.jokesRequest = jokesRequest
    }

    fun handleEvents(event: Event?) {
        when (event) {
            Event.SEARCH -> requestSearchJokesList()
            Event.SUBMIT -> {}
            else -> {}
        }
    }

    private fun requestSearchJokesList() {
        var amount = 0
        if (jokesRequest?.amount != null && jokesRequest?.amount!!.isNotEmpty())
            amount = jokesRequest?.amount?.toInt()!!
        if (amount > 1) {
            jokesResponseLiveData = jokesRepository.getJokesList(
            jokesRequest?.category,
            jokesRequest?.jokeType,
            jokesRequest?.amount)
        } else {
            singleJokeResponseLiveData = jokesRepository.getSingleJokeList(
                jokesRequest?.category,
                jokesRequest?.jokeType,
                jokesRequest?.amount
            )
        }
    }
}