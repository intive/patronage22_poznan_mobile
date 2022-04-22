package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.databinding.FragmentGenresBinding
import com.intive.patronage22.intivi.viewmodel.GenresViewModel

class GenresFragment : Fragment() {

    private lateinit var bind: FragmentGenresBinding
    private val genresViewModel: GenresViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentGenresBinding.inflate(layoutInflater)

        genresViewModel.apiError.observe(viewLifecycleOwner) {
            bind.errorTextView?.text = it
            if (it != null) {
                bind.errorTextView?.visibility = View.VISIBLE
            } else bind.errorTextView?.visibility = View.GONE
        }

        genresViewModel.genresList.observe(viewLifecycleOwner) {
            if (it != null) {
                populateGenres()
            }
        }

        genresViewModel.fetchGenres()
        return bind.root
    }

    private fun populateGenres() {
        val genresNameList = arrayListOf<String>()
        for (genre in genresViewModel.genresList.value!!) {
            genresNameList.add(genre.name)
        }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(), R.layout.genres_text, genresNameList
        )
        bind.genresList.adapter = arrayAdapter
    }

}