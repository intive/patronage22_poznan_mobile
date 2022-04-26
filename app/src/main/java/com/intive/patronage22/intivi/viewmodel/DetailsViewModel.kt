package com.intive.patronage22.intivi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.model.FavouriteMovieResponse
import com.intive.patronage22.intivi.model.MovieItem
import com.intive.patronage22.intivi.model.MovieResponse
import com.intive.patronage22.intivi.model.MovieResponseParser.parseMovieResponseToMovieItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieItem>()
    val movieDetails: LiveData<MovieItem> = _movieDetails

    private val _apiError = MutableLiveData<String?>(null)
    val apiError: LiveData<String?> = _apiError

    private val _favouriteMoviesList = MutableLiveData<List<MovieItem>>()
    private val favouriteMoviesList: LiveData<List<MovieItem>> = _favouriteMoviesList

    private val _apiErrorFavouriteOperation = MutableLiveData<String?>(null)
    val apiErrorFavouriteOperation: LiveData<String?> = _apiErrorFavouriteOperation

    private val _isFavourite = MutableLiveData<Boolean>(false)
    val isFavourite: LiveData<Boolean> = _isFavourite

    private var _movieId: Int? = null

    fun setApiError(error: String){
        _apiError.value = error
    }

    fun getMovieDetails(movieId: Int) {
        ApiClient().getService()?.getMovieDetails(movieId)?.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _apiError.value = t.message
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    _apiError.value = null
                    _movieDetails.value = parseMovieResponseToMovieItem(responseBody!!)
                    _movieId = movieDetails.value!!.id
                    fetchFavourites()
                } else {
                    _apiError.value = response.code().toString()
                }
            }
        })
    }

    fun fetchFavourites() {
        ApiClient().getService()?.fetchFavourites()
            ?.enqueue(object : Callback<List<FavouriteMovieResponse>> {
                override fun onFailure(call: Call<List<FavouriteMovieResponse>>, t: Throwable) {
                    _apiError.value = t.message
                }

                override fun onResponse(
                    call: Call<List<FavouriteMovieResponse>>,
                    response: Response<List<FavouriteMovieResponse>>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        if (responseBody != null) {
                            _apiError.value = null
                            _favouriteMoviesList.value =
                                parseMovieResponseToMovieItem(responseBody)
                            _isFavourite.value = checkFavouriteStatus(_movieId!!)
                        }
                    } else {
                        _apiError.value = response.code().toString()
                    }
                }
            })
    }

    fun putFavourite() {
        ApiClient().getService()?.putFavourites(_movieId!!)?.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    _apiErrorFavouriteOperation.value = null
                    fetchFavourites()
                } else {
                    _isFavourite.value = false
                    _apiErrorFavouriteOperation.value = response.code().toString()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                _isFavourite.value = false
                _apiErrorFavouriteOperation.value = t.message
            }
        })
    }

    fun deleteFavourite() {
        ApiClient().getService()?.deleteFavourites(_movieId!!)?.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    _apiErrorFavouriteOperation.value = null
                    fetchFavourites()
                } else {
                    _isFavourite.value = true
                    _apiErrorFavouriteOperation.value = response.code().toString()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                _isFavourite.value = true
                _apiErrorFavouriteOperation.value = t.message
            }
        })
    }

    fun checkFavouriteStatus(movieID: Int): Boolean {
        return if (favouriteMoviesList.value != null) {
            favouriteMoviesList.value!!.find { it.id == movieID } != null
        } else false
    }
}