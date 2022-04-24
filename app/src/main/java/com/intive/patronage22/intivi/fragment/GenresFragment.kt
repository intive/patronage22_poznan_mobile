package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.adapter.GenresListAdapter
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.databinding.FragmentGenresBinding
import com.intive.patronage22.intivi.viewmodel.HomeViewModel

class GenresFragment : Fragment() {

    private lateinit var bind: FragmentGenresBinding
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentGenresBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apiError.observe(viewLifecycleOwner) {
            bind.errorTextView?.text = it
            if (it != null) {
                bind.errorTextView?.visibility = View.VISIBLE
            } else bind.errorTextView?.visibility = View.GONE
        }

        viewModel.genresList.observe(viewLifecycleOwner) {
            if (it != null) {
                bind.genresRecycler?.adapter = GenresListAdapter(viewModel.genresList.value!!, viewModel, activity
                )
            }
        }

        viewModel.fetchGenres()

        bind.genresRecycler?.apply {
            if (viewModel.genresList.value != null) {
                adapter = GenresListAdapter(viewModel.genresList.value!!, viewModel, activity)
            }
        }
    }

}