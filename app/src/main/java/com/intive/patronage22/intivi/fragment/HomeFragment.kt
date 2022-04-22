package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding
import com.intive.patronage22.intivi.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            if (homeViewModel.popularMoviesList.value != null) {
                adapter = MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
            }
        }

        //TODO update individual items instead of the whole dataset
        homeViewModel.popularMoviesList.observe(viewLifecycleOwner) {
            bind.recyclerView.adapter =
                MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
            if (it.isNotEmpty()) {
                bind.noMoviesErrorTextView.visibility = View.GONE
            } else {
                bind.noMoviesErrorTextView.visibility = View.VISIBLE
            }
        }

        homeViewModel.favouriteMoviesListResponse.observe(viewLifecycleOwner) {
            if (homeViewModel.popularMoviesList.value != null) {
                bind.recyclerView.adapter =
                    MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
            }
        }

        waitForMovies()

    }

    private fun waitForMovies() {
        lifecycleScope.launch {
            delay(15000)
            bind.noMoviesErrorTextView.text = getString(R.string.no_movies_error_message)
        }
    }
}