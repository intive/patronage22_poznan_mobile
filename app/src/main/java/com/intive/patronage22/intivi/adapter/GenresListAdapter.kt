package com.intive.patronage22.intivi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.model.Genres
import com.intive.patronage22.intivi.viewmodel.HomeViewModel


class GenresListAdapter(
    private val movieResponseItemList: List<Genres>,
    private val viewModel: HomeViewModel,
    private val activity: FragmentActivity?
) : RecyclerView.Adapter<GenresListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.genres_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: GenresListAdapter.ViewHolder, position: Int) {
        holder.itemGenreId = movieResponseItemList[position].id
        holder.itemTitle.text = movieResponseItemList[position].name

        holder.itemTitle.setOnClickListener {
            if (holder.itemGenreId != null) {
                viewModel.fetchGenreMembers(holder.itemGenreId!!)
                activity?.findViewById<TabLayout>(R.id.footer_tab)?.getTabAt(0)!!.select()
            }
        }
    }

    override fun getItemCount(): Int {
        return movieResponseItemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemGenreId: Int? = null
        var itemTitle: TextView = itemView.findViewById(R.id.genre_title)
    }
}