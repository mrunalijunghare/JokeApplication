package com.example.jokeapplication.view

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapplication.R
import com.example.jokeapplication.adapter.JokesAdapter
import com.example.jokeapplication.model.Event
import com.example.jokeapplication.model.JokeClass
import com.example.jokeapplication.model.JokesRequest
import com.example.jokeapplication.model.JokesResponse
import com.example.jokeapplication.model.Util.KEY_JOKE_REQUEST
import com.example.jokeapplication.viewmodel.JokesViewModel

class JokesListActivity : AppCompatActivity() {
    private var jokesViewModel: JokesViewModel? = null
    private var recyclerView: RecyclerView? = null
    private var jokesAdapter: JokesAdapter? = null
    private val jokesArraylist = ArrayList<JokeClass>()
    private var layoutError: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        init()
        populateJokesList()
    }

    private fun init() {
        jokesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(JokesViewModel::class.java)
        setDataFromIntent()
        layoutError = findViewById(R.id.layoutError)
        val layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView?.layoutManager = layoutManager
        jokesAdapter = JokesAdapter(jokesArraylist)
        recyclerView?.adapter = jokesAdapter
        makeRequestForJokesList()
    }

    private fun makeRequestForJokesList() {
        jokesViewModel?.handleEvents(Event.SEARCH)
    }

    private fun setDataFromIntent() {
        val jokesRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(KEY_JOKE_REQUEST, JokesRequest::class.java)
        } else {
            intent.getSerializableExtra(KEY_JOKE_REQUEST) as JokesRequest
        }
        if (jokesRequest != null) jokesViewModel?.setJokesRequest(jokesRequest)
    }

    private fun populateJokesList() {
        jokesViewModel?.jokesResponseLiveData?.observe(this) { jokesResponse: JokesResponse? ->
            if (jokesResponse == null) {
                recyclerView?.visibility = View.GONE
                layoutError?.visibility = View.VISIBLE
            } else if (jokesResponse.arrayListJokes != null) {
                jokesArraylist.addAll(jokesResponse.arrayListJokes!!)
                jokesAdapter?.notifyDataSetChanged()
            }
        }
        jokesViewModel?.singleJokeResponseLiveData?.observe(this){ joke: JokeClass? ->
            if (joke != null) {
                jokesArraylist.add(joke)
                jokesAdapter?.notifyItemChanged(0)
            } else {
                recyclerView?.visibility = View.GONE
                layoutError?.visibility = View.VISIBLE
            }
        }
    }

}