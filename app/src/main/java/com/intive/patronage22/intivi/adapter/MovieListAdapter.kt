package com.intive.patronage22.intivi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.model.MovieItem
import com.intive.patronage22.intivi.model.OpenDetailsEvent
import com.intive.patronage22.intivi.viewmodel.HomeViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MovieListAdapter(
    private val movieResponseItemList: List<MovieItem>,
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_home, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.itemMovieId = movieResponseItemList[position].id
        holder.itemTitle.text = movieResponseItemList[position].title
        Picasso.get().load(movieResponseItemList[position].posterXlUrl)
            .error(R.drawable.poster_error_xl)
            .into(holder.itemImage, object: Callback {
                override fun onSuccess(){
                    holder.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception) {
                    holder.progressBar.visibility = View.GONE
                }
            })

        if (viewModel.checkFavouriteStatus(holder.itemMovieId!!)) {
            holder.itemFavourite.setBackgroundResource(R.drawable.ic_favourite_grid_item_fill)
        } else {
            holder.itemFavourite.setBackgroundResource(R.drawable.ic_favourite_grid_item)
        }

        holder.itemFavourite.setOnClickListener { _ ->
            if (viewModel.checkFavouriteStatus(holder.itemMovieId!!)) {
                viewModel.deleteFavourite(holder.itemMovieId!!)
                holder.itemFavourite.setBackgroundResource(R.drawable.ic_favourite_grid_item)
            } else {
                viewModel.putFavourite(holder.itemMovieId!!)
                holder.itemFavourite.setBackgroundResource(R.drawable.ic_favourite_grid_item_fill)
            }
        }

        holder.itemImage.setOnClickListener {
            if (holder.itemMovieId != null) {
                viewModel.setDetailsEvent(OpenDetailsEvent(holder.itemMovieId))
            }
        }
    }

    override fun getItemCount(): Int {
        return movieResponseItemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemMovieId: Int? = null
        var itemImage: ImageView = itemView.findViewById(R.id.movieAvatar)
        var itemTitle: TextView = itemView.findViewById(R.id.movieTitle)
        var itemFavourite: ImageButton = itemView.findViewById(R.id.movieFavouritesCheckBox)
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}