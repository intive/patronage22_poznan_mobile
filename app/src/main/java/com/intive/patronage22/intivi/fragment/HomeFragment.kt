package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding
import com.intive.patronage22.intivi.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        searchView = bind.searchView
        recyclerView = bind.recyclerView
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                homeViewModel.fetchSearchQuery(query)
                searchView.clearFocus()
                bind.appBarLayout.setExpanded(false)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        searchView.setOnQueryTextFocusChangeListener { _, queryTextFocused ->
            if (!queryTextFocused && searchView.query.isEmpty()) {
                searchView.isIconified = true
            }
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            if (homeViewModel.popularMoviesList.value != null) {
                adapter = MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
            }
        }

        //TODO update individual items instead of the whole dataset
        homeViewModel.popularMoviesList.observe(viewLifecycleOwner) {
            recyclerView.adapter =
                MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
            if (it.isNotEmpty()) {
                bind.errorTextView.visibility = View.GONE
                bind.loadingBar.visibility = View.GONE
            } else {
                bind.errorTextView.visibility = View.VISIBLE
            }
        }

        homeViewModel.favouriteMoviesList.observe(viewLifecycleOwner) {
            if (homeViewModel.popularMoviesList.value != null) {
                recyclerView.adapter =
                    MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
            }
        }

        homeViewModel.apiErrorHome.observe(viewLifecycleOwner) {
            bind.errorTextView.text = it
            if (it != null) {
                bind.errorTextView.visibility = View.VISIBLE
            } else {
                bind.errorTextView.visibility = View.GONE
            }
        }

        bind.filterFirst.setOnClickListener {
            homeViewModel.fetchPopular()
        }

        bind.filterSecond.setOnClickListener {
            homeViewModel.fetchGenreMembers(16)
        }

        bind.filterThird.setOnClickListener {
            homeViewModel.fetchGenreMembers(27)
        }

        bind.filterFourth.setOnClickListener {
            homeViewModel.fetchGenreMembers(18)
        }

    }

    override fun onResume() {
        super.onResume()
        homeViewModel.fetchFavourites()
    }
}
