package com.intive.patronage22.intivi

import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.databinding.ItemGridHomeBinding

class CardViewHolder (private val itemGridHomeBinding: ItemGridHomeBinding):RecyclerView.ViewHolder(itemGridHomeBinding.root) {
    fun bindCard(card:Card){
        itemGridHomeBinding.movieAvatar.setImageResource(card.movieAvatar)
        itemGridHomeBinding.movieTitle.text = card.movieTitle
    }
}