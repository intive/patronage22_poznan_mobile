package com.intive.patronage22.intivi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.model.MovieItem
import com.intive.patronage22.intivi.model.MovieResponse
import com.intive.patronage22.intivi.model.MovieResponseParser.parseMovieResponseToMovieItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieItem>()
    val movieResponseDetails: LiveData<MovieItem> = _movieDetails

    private val _apiError = MutableLiveData<String?>(null)
    val apiError: LiveData<String?> = _apiError

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
                } else {
                    _apiError.value = response.code().toString()
                }
            }
        })
    }
}