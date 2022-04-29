package com.intive.patronage22.intivi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.model.MovieItem
import com.intive.patronage22.intivi.model.OpenDetailsEvent
import com.intive.patronage22.intivi.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class MovieListAdapter(
    movieResponseItemList: List<MovieItem>?,
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var data: MutableList<MovieItem>? = movieResponseItemList?.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_home, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {

        data?.let {
            it[position].let { movie ->
                holder.itemMovieId = movie.id
                holder.itemTitle.text = movie.title
                holder.itemFavourite.isChecked = viewModel.checkFavouriteStatus(holder.itemMovieId!!)
                Picasso.get().load(movie.posterXlUrl)
                    .error(R.drawable.app_logo)
                    .into(holder.itemImage)

                holder.itemFavourite.setOnClickListener {
                    holder.itemFavourite.isSelected = !holder.itemFavourite.isSelected
                    viewModel.handleFavouriteRequest(holder.itemMovieId!!)
                }

                holder.itemImage.setOnClickListener {
                    if (holder.itemMovieId != null) {
                        viewModel.setDetailsEvent(OpenDetailsEvent(holder.itemMovieId))
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemMovieId: Int? = null
        var itemImage: ImageView = itemView.findViewById(R.id.movieAvatar)
        var itemTitle: TextView = itemView.findViewById(R.id.movieTitle)
        var itemFavourite: CheckBox = itemView.findViewById(R.id.movieFavouritesCheckBox)
    }

    fun removeItemAt(index: Int){
        data?.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, itemCount)
    }
}