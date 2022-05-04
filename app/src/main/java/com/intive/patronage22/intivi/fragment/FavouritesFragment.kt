package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.databinding.FragmentFavouritesBinding
import com.intive.patronage22.intivi.viewmodel.HomeViewModel

class FavouritesFragment : Fragment() {

    private lateinit var bind: FragmentFavouritesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieListAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentFavouritesBinding.inflate(inflater, container, false)
        recyclerView = bind.recyclerView
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = MovieListAdapter(homeViewModel.favouriteMoviesList.value, homeViewModel)
        }
        adapter = recyclerView.adapter as MovieListAdapter

        homeViewModel.favouriteMoviesList.observe(viewLifecycleOwner) {
            when (it.isNotEmpty()) {
                true -> bind.loadingBar.visibility = View.GONE
                false -> bind.errorTextView.visibility = View.VISIBLE
            }
            adapter.updateData(homeViewModel.favouriteMoviesList.value!!)
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

    override fun onResume() {
        super.onResume()
        homeViewModel.fetchFavourites()
    }

}