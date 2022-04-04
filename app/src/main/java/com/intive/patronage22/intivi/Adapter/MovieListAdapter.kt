package com.intive.patronage22.intivi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.model.MovieItem

class MovieListAdapter:RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var movieList: List<MovieItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_home, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = movieList!![position].title
        holder.itemImage.setImageResource(movieList!![position].image)
    }

    fun setMovieList(list: List<MovieItem>) {
        movieList = list
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView = itemView.findViewById(R.id.movieAvatar)
        var itemTitle: TextView = itemView.findViewById(R.id.movieTitle)
    }
}