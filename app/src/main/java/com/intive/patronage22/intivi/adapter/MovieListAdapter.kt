package com.intive.patronage22.intivi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.model.Movie
//import com.intive.patronage22.intivi.model.MovieItem
import com.squareup.picasso.Picasso

class MovieListAdapter(private val MovieItemList: List<Movie>):RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_home, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = MovieItemList[position].title
        Picasso.get().load(MovieItemList[position].images.poster.xl).error(R.drawable.app_logo).into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return MovieItemList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView = itemView.findViewById(R.id.movieAvatar)
        var itemTitle: TextView = itemView.findViewById(R.id.movieTitle)
    }
}