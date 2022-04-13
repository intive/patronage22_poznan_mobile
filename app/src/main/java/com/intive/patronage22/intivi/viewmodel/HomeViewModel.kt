package com.intive.patronage22.intivi.viewmodel

import androidx.lifecycle.ViewModel
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.model.MovieItem

class HomeViewModel: ViewModel() {

    var homeItemsList = mutableListOf<MovieItem>()
    var filteredItemsList = mutableListOf<MovieItem>()

    enum class HomeItemType{
        MOVIES, SERIES, KIDS
    }

    fun createHomeItems(){
        homeItemsList.add(MovieItem("Moonfal",R.drawable.avatar_moonfall, HomeItemType.MOVIES))
        homeItemsList.add(MovieItem("Sing 2",R.drawable.avatar_sing_2, HomeItemType.KIDS))
        homeItemsList.add(MovieItem("The Expanse",R.drawable.avatar_the_expanse, HomeItemType.SERIES))
        homeItemsList.add(MovieItem("Death on the Nile",R.drawable.avatar_death_on_the_nile, HomeItemType.MOVIES))
        homeItemsList.add(MovieItem("Little Nicholas' Treasure",R.drawable.avatar_little_nicholas_treasure, HomeItemType.KIDS))
        homeItemsList += homeItemsList + homeItemsList
    }

    fun moviesItemsFilter(): MutableList<MovieItem>{
        filteredItemsList.clear()
        for(item in homeItemsList) {
            if(item.type == HomeItemType.MOVIES){
                filteredItemsList.add(item)
            }
        }
        return filteredItemsList
    }

    fun seriesCardFilter():MutableList<MovieItem>{
        filteredItemsList.clear()
        for(item in homeItemsList) {
            if(item.type == HomeItemType.SERIES){
                filteredItemsList.add(item)
            }
        }
        return filteredItemsList
    }

    fun kidsCardFilter():MutableList<MovieItem>{
        filteredItemsList.clear()
        for(item in homeItemsList) {
            if(item.type == HomeItemType.KIDS){
                filteredItemsList.add(item)
            }
        }
        return filteredItemsList
    }
}