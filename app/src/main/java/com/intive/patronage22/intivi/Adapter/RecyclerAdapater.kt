package com.intive.patronage22.intivi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.R

class RecyclerAdapater:RecyclerView.Adapter<RecyclerAdapater.ViewHolder>() {
    private var titles = arrayOf("Moonfal","sdad")
    private var images = intArrayOf(R.drawable.moonfall_image, R.drawable.moonfall_image)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_home, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView

        init {
            itemImage = itemView.findViewById(R.id.avatar)
            itemTitle = itemView.findViewById(R.id.title)
        }
    }
}