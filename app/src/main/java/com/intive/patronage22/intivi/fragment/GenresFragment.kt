package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.viewmodel.LoginViewModel
import com.intive.patronage22.intivi.databinding.FragmentGenresBinding
import com.intive.patronage22.intivi.model.Genres
import com.intive.patronage22.intivi.model.GenresResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresFragment : Fragment() {

    private lateinit var bind: FragmentGenresBinding
    private val loginViewModel: LoginViewModel by activityViewModels()

    private lateinit var genres: List<Genres>
    var genres_name = arrayListOf<String>()
    var genres_id = arrayListOf<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentGenresBinding.inflate(layoutInflater)
        fetchGenres()
        return bind.root
    }
    private fun fetchGenres() {
        ApiClient().getService()?.fetchGenres()
            ?.enqueue(object : Callback<GenresResponse> {
                override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                }

                override fun onResponse(call: Call<GenresResponse>, response: Response<GenresResponse>) {
                    val loginResponse = response.body()
                    if (loginResponse?.genres != null) {
                        genres = loginResponse.genres
                        setGenresElements()
                        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                            requireContext(), R.layout.genres_text, genres_name)
                        bind.genresList.adapter = arrayAdapter
                    }
                }
            })
    }

    private fun setGenresElements(){
        genres_id.clear()
        genres_name.clear()
        for(genre in genres)
        {
            genres_id.add(genre.id)
            genres_name.add(genre.name)
        }
    }
}