package com.example.jokeapplication.adapter

import com.example.jokeapplication.model.JokeClass
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.jokeapplication.R
import android.widget.TextView
import java.util.ArrayList

class JokesAdapter(private val jokesArrayList: ArrayList<JokeClass>) :
    RecyclerView.Adapter<JokesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jokeClass = jokesArrayList[position]
        if (jokeClass.joke != null) holder.tvJoke.text = jokeClass.joke
        if (jokeClass.setup != null) holder.tvJoke.text = jokeClass.setup
        if (jokeClass.delivery != null) {
            holder.tvDelivery.visibility = View.VISIBLE
            holder.tvDelivery.text = jokeClass.delivery
        }
    }

    override fun getItemCount(): Int {
        return jokesArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJoke: TextView
        val tvDelivery: TextView

        init {
            tvJoke = itemView.findViewById(R.id.tvJoke)
            tvDelivery = itemView.findViewById(R.id.tvDelivery)
        }
    }
}