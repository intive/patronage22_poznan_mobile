package com.intive.patronage22.intivi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.model.FavouriteMovie
import com.intive.patronage22.intivi.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val _popularMoviesList = MutableLiveData<List<Movie>>()
    val popularMoviesList: LiveData<List<Movie>> = _popularMoviesList

    private val _favouriteMoviesList = MutableLiveData<List<FavouriteMovie>>()
    val favouriteMoviesList: LiveData<List<FavouriteMovie>> = _favouriteMoviesList

    init{
        fetchFavourites()
        fetchPopular()
    }

    fun fetchPopular() {
        ApiClient().getService()?.fetchPopular()?.enqueue(object : Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                //Handle failure
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _popularMoviesList.value = responseBody!!
                    }
                } else {
                    //Handle error
                }
            }
        })
    }

    fun fetchFavourites() {
        ApiClient().getService()?.fetchFavourites()?.enqueue(object : Callback<List<FavouriteMovie>> {
            override fun onFailure(call: Call<List<FavouriteMovie>>, t: Throwable) {
                //handle failure
            }

            override fun onResponse(call: Call<List<FavouriteMovie>>, response: Response<List<FavouriteMovie>>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _favouriteMoviesList.value = responseBody!!
                    }
                } else {
                    //handle error
                }
            }
        })
    }

    fun putFavourite(movieID: Int) {
        ApiClient().getService()?.putFavourites(movieID)?.enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful){
                    fetchFavourites()
                } else {
                    //handle error
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                //handle failure
            }
        })
    }

    fun deleteFavourite(movieID: Int) {
        ApiClient().getService()?.deleteFavourites(movieID)?.enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful){
                    fetchFavourites()
                } else {
                    //handle error
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                //handle failure
            }
        })
    }

    fun checkFavouriteStatus(movieID: Int): Boolean{
        if(favouriteMoviesList.value!=null) {
            return favouriteMoviesList.value!!.find { it.id == movieID } != null
        } else return false
    }

}