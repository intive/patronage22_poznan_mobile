package com.intive.patronage22.intivi.Adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.Card
import com.intive.patronage22.intivi.CardViewHolder
import com.intive.patronage22.intivi.databinding.ItemGridHomeBinding

class RecyclerAdapter(private val cards: List<Card>):RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val v = ItemGridHomeBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CardViewHolder(v)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindCard(cards[position])
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}