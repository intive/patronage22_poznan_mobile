package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.databinding.FragmentFavouritesBinding
import com.intive.patronage22.intivi.viewmodel.HomeViewModel

class FavouritesFragment : Fragment() {
    private lateinit var bind: FragmentFavouritesBinding
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentFavouritesBinding.inflate(inflater, container, false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            if (homeViewModel.favouriteMoviesList.value != null) {
                adapter =
                    MovieListAdapter(
                        homeViewModel.favouriteMoviesList.value!!,
                        homeViewModel
                    )
            }
        }

        homeViewModel.favouriteMoviesList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                bind.recyclerView.adapter =
                    MovieListAdapter(homeViewModel.favouriteMoviesList.value!!, homeViewModel)
                bind.loadingBar.visibility = View.GONE
            } else {
                bind.errorTextView.visibility = View.VISIBLE
            }
        }

        homeViewModel.apiErrorFavourites.observe(viewLifecycleOwner) {
            bind.errorTextView.text = it
            if (it != null) {
                bind.errorTextView.visibility = View.VISIBLE
            } else {
                bind.errorTextView.visibility = View.GONE
            }
        }
    }
}