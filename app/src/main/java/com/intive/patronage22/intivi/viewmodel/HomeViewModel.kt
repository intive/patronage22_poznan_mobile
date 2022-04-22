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
    val favouriteMoviesList: LiveData<List<MovieItem>> = _favouriteMoviesList

    private val _apiErrorFavourites = MutableLiveData<String?>(null)
    val apiErrorFavourites: LiveData<String?> = _apiErrorFavourites

    private val _apiErrorHome = MutableLiveData<String?>(null)
    val apiErrorHome: LiveData<String?> = _apiErrorHome

    private val _apiErrorFavouriteOperation = MutableLiveData<String?>(null)
    val apiErrorFavouriteOperation: LiveData<String?> = _apiErrorFavouriteOperation

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
                _apiErrorHome.value = t.message
            }

            override fun onResponse(
                call: Call<List<MovieResponse>>,
                response: Response<List<MovieResponse>>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _apiErrorHome.value = null
                        _popularMoviesList.value =
                            MovieResponseParser.parseMovieResponseToMovieItem(responseBody)
                    }
                } else {
                    _apiErrorHome.value = response.code().toString()
                }
            }
        })
    }

    fun fetchFavourites() {
        ApiClient().getService()?.fetchFavourites()
            ?.enqueue(object : Callback<List<FavouriteMovieResponse>> {
                override fun onFailure(call: Call<List<FavouriteMovieResponse>>, t: Throwable) {
                    _apiErrorFavourites.value = t.message
                }

                override fun onResponse(
                    call: Call<List<FavouriteMovieResponse>>,
                    response: Response<List<FavouriteMovieResponse>>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        if (responseBody != null) {
                            _apiErrorFavourites.value = null
                            _favouriteMoviesList.value =
                                MovieResponseParser.parseMovieResponseToMovieItem(responseBody)
                        }
                    } else {
                        _apiErrorFavourites.value = response.code().toString()
                    }
                }
            })
    }

    fun putFavourite(movieID: Int) {
        ApiClient().getService()?.putFavourites(movieID)?.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    _apiErrorFavouriteOperation.value = null
                    fetchFavourites()
                } else {
                    _apiErrorFavouriteOperation.value = response.code().toString()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                _apiErrorFavouriteOperation.value = t.message
            }
        })
    }

    fun deleteFavourite(movieID: Int) {
        ApiClient().getService()?.deleteFavourites(movieID)?.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    _apiErrorFavouriteOperation.value = null
                    fetchFavourites()
                } else {
                    _apiErrorFavouriteOperation.value = response.code().toString()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                _apiErrorFavouriteOperation.value = t.message
            }
        })
    }

    fun checkFavouriteStatus(movieID: Int): Boolean {
        if (favouriteMoviesList.value != null) {
            return favouriteMoviesList.value!!.find { it.id == movieID } != null
        } else return false
    }
}