package com.intive.patronage22.intivi.viewmodel

import android.app.Application
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.model.Genres
import com.intive.patronage22.intivi.model.GenresResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresViewModel: ViewModel() {

    private val _genresList = MutableLiveData<List<Genres>>()
    val genresList: LiveData<List<Genres>> = _genresList

    private val _genresApiSuccess = MutableLiveData(false)
    val genresApiSuccess: LiveData<Boolean> = _genresApiSuccess

    fun fetchGenres() {
        ApiClient().getService()?.fetchGenres()?.enqueue(object : Callback<GenresResponse> {
            override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                _genresApiSuccess.value = false
            }

            override fun onResponse(call: Call<GenresResponse>, response: Response<GenresResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody?.genres != null) {
                        _genresList.value = responseBody.genres
                        _genresApiSuccess.value = true
                    }
                } else {
                    _genresApiSuccess.value = false
                }
            }
        })
    }
}