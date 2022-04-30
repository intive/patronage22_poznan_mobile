package com.intive.patronage22.intivi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.databinding.ItemGridHomeBinding
import com.intive.patronage22.intivi.model.MovieItem
import com.intive.patronage22.intivi.model.OpenDetailsEvent
import com.intive.patronage22.intivi.viewmodel.HomeViewModel

class MovieListAdapter(
    private val movieResponseItemList: List<MovieItem>,
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_grid_home,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemGridHomeBinding.movies = movieResponseItemList[position]
        holder.itemMovieId = movieResponseItemList[position].id

        if (viewModel.checkFavouriteStatus(holder.itemMovieId!!)) {
            holder.itemGridHomeBinding.movieFavouritesCheckBox.setBackgroundResource(R.drawable.ic_favourite_grid_item_fill)
        } else {
            holder.itemGridHomeBinding.movieFavouritesCheckBox.setBackgroundResource(R.drawable.ic_favourite_grid_item)
        }

        holder.itemGridHomeBinding.movieFavouritesCheckBox.setOnClickListener { _ ->
            if (viewModel.checkFavouriteStatus(holder.itemMovieId!!)) {
                viewModel.deleteFavourite(holder.itemMovieId!!)
                holder.itemGridHomeBinding.movieFavouritesCheckBox.setBackgroundResource(R.drawable.ic_favourite_grid_item)
            } else {
                viewModel.putFavourite(holder.itemMovieId!!)
                holder.itemGridHomeBinding.movieFavouritesCheckBox.setBackgroundResource(R.drawable.ic_favourite_grid_item_fill)
            }
        }

        holder.itemGridHomeBinding.movieAvatar.setOnClickListener {
            if (holder.itemMovieId != null) {
                viewModel.setDetailsEvent(OpenDetailsEvent(holder.itemMovieId!!))
            }
        }
    }

    override fun getItemCount(): Int {
        return movieResponseItemList.size
    }

    inner class ViewHolder(val itemGridHomeBinding: ItemGridHomeBinding) : RecyclerView.ViewHolder(itemGridHomeBinding.root) {
        var itemMovieId: Int? = null
    }
}