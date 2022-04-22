package com.intive.patronage22.intivi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _openDetailsEvent = MutableLiveData(OpenDetailsEvent(null))
    val openDetailsEvent: LiveData<OpenDetailsEvent> = _openDetailsEvent

    private val _popularMoviesList = MutableLiveData<List<MovieItem>>()
    val popularMoviesList: LiveData<List<MovieItem>> = _popularMoviesList

    private val _favouriteMoviesList = MutableLiveData<List<MovieItem>>()
    val favouriteMoviesListResponse: LiveData<List<MovieItem>> = _favouriteMoviesList

    init {
        fetchFavourites()
        fetchPopular()
    }

    fun setDetailsEvent(detailsEvent: OpenDetailsEvent) {
        _openDetailsEvent.value = detailsEvent
    }

    fun fetchPopular() {
        ApiClient().getService()?.fetchPopular()?.enqueue(object : Callback<List<MovieResponse>> {
            override fun onFailure(call: Call<List<MovieResponse>>, t: Throwable) {
                //Handle failure
            }

            override fun onResponse(call: Call<List<MovieResponse>>, response: Response<List<MovieResponse>>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _popularMoviesList.value = MovieResponseParser.parseMovieResponseToMovieItem(responseBody!!)
                    }
                } else {
                    //Handle error
                }
            }
        })
    }

    fun fetchFavourites() {
        ApiClient().getService()?.fetchFavourites()
            ?.enqueue(object : Callback<List<FavouriteMovieResponse>> {
                override fun onFailure(call: Call<List<FavouriteMovieResponse>>, t: Throwable) {
                    //handle failure
                }

                override fun onResponse(
                    call: Call<List<FavouriteMovieResponse>>,
                    response: Response<List<FavouriteMovieResponse>>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        if (responseBody != null) {
                            _favouriteMoviesList.value = MovieResponseParser.parseMovieResponseToMovieItem(responseBody!!)
                        }
                    } else {
                        //handle error
                    }
                }
            })
    }

    fun putFavourite(movieID: Int) {
        ApiClient().getService()?.putFavourites(movieID)?.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
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
        ApiClient().getService()?.deleteFavourites(movieID)?.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
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

    fun checkFavouriteStatus(movieID: Int): Boolean {
        if (favouriteMoviesListResponse.value != null) {
            return favouriteMoviesListResponse.value!!.find { it.id == movieID } != null
        } else return false
    }
}