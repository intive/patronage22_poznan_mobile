package com.intive.patronage22.intivi.viewmodel

import android.util.Log
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

    fun getMovieDetails(movieId: Int) {
        ApiClient().getService()?.getMovieDetails(movieId)?.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("bayraktar", "fetch details failure = $t, movieId = $movieId")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    Log.d("bayraktar", "fetch details success! response = $responseBody")
                    _movieDetails.value = parseMovieResponseToMovieItem(responseBody!!)
                } else {
                    Log.d(
                        "bayraktar",
                        "fetch details error = ${response.code()}, movieId = $movieId"
                    )
                }
            }
        })
    }
}