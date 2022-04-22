package com.intive.patronage22.intivi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.model.Movie
import com.intive.patronage22.intivi.model.OpenDetailsEvent
import com.intive.patronage22.intivi.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class MovieListAdapter(private val MovieItemList: List<Movie>, private val viewModel: HomeViewModel):RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_home, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.itemMovieId = MovieItemList[position].id
        holder.itemTitle.text = MovieItemList[position].title
        Picasso.get().load(MovieItemList[position].images.poster.xl).error(R.drawable.app_logo).into(holder.itemImage)
        if(viewModel.checkFavouriteStatus(holder.itemMovieId!!)){
            holder.itemFavourite.setBackgroundResource(R.drawable.ic_favourite_grid_item_fill)
        } else {
            holder.itemFavourite.setBackgroundResource(R.drawable.ic_favourite_grid_item)
        }

        holder.itemFavourite.setOnClickListener { _ ->
            if(viewModel.checkFavouriteStatus(holder.itemMovieId!!)){
                viewModel.deleteFavourite(holder.itemMovieId!!)
                holder.itemFavourite.setBackgroundResource(R.drawable.ic_favourite_grid_item)
            } else {
                viewModel.putFavourite(holder.itemMovieId!!)
                holder.itemFavourite.setBackgroundResource(R.drawable.ic_favourite_grid_item_fill)
            }
        }

        holder.itemImage.setOnClickListener {
            if (holder.itemMovieId != null) {
                Log.d("bayraktar", "setting details event with id = ${holder.itemMovieId}")
                viewModel.setDetailsEvent(OpenDetailsEvent(holder.itemMovieId))
            }
        }
    }

    override fun getItemCount(): Int {
        return MovieItemList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemMovieId: Int? = null
        var itemImage: ImageView = itemView.findViewById(R.id.movieAvatar)
        var itemTitle: TextView = itemView.findViewById(R.id.movieTitle)
        var itemFavourite: ImageButton = itemView.findViewById(R.id.movieFavouritesCheckBox)
    }
}