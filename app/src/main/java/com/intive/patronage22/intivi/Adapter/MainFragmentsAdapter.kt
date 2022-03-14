package com.intive.patronage22.intivi.Adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.intive.patronage22.intivi.Fragments.*

internal class MainFragmentsAdapter (fa: FragmentActivity, var totalTabs: Int): FragmentStateAdapter(fa) {

    enum class ActiveTab {
        HOME, FAVOURITES, GENRES
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            ActiveTab.HOME.ordinal -> {
                HomeFragment()
            }
            ActiveTab.FAVOURITES.ordinal-> {
                FavouritesFragment()
            }
            ActiveTab.GENRES.ordinal-> {
                GenresFragment()
            }
            else-> createFragment(position)
        }
    }

    override fun getItemCount(): Int {
        return totalTabs
    }
}