package com.intive.patronage22.intivi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel: ViewModel() {

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> = _movieDetails

    fun getMovieDetails(movieId: Int){
        ApiClient().getService()?.getMovieDetails(movieId)?.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("bayraktar", "fetch details failure = $t, movieId = $movieId")
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    Log.d("bayraktar", "fetch details success! response = $responseBody")
                    _movieDetails.value = responseBody!!
                } else {
                    Log.d("bayraktar", "fetch details error = ${response.code()}, movieId = $movieId")
                }
            }
        })
    }
}