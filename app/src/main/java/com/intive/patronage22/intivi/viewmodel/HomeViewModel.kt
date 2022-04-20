package com.intive.patronage22.intivi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.model.MovieItem
import com.intive.patronage22.intivi.model.Movie
import com.intive.patronage22.intivi.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    var homeItemsList = mutableListOf<MovieItem>()
    var filteredItemsList = mutableListOf<MovieItem>()

    private val _popularMoviesList = MutableLiveData<List<Movie>>()
    val popularMoviesList: LiveData<List<Movie>> = _popularMoviesList

    enum class HomeItemType{
        MOVIES, SERIES, KIDS
    }

    init{fetchPopular()}

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

    fun fetchPopular() {
        Log.d("bayraktar", "attempting to fetch popular")
        ApiClient().getService()?.fetchPopular()?.enqueue(object : Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d("bayraktar", "Popular fetch failure. T =  $t")
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _popularMoviesList.value = responseBody!!
                        Log.d("bayraktar", "Popular movies fetch successful. Response: ${responseBody}")
                    }
                } else {
                    Log.d("bayraktar", "Fetch response error code = ${response.code()}")
                }
            }
        })
    }

}