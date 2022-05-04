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
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding
import com.intive.patronage22.intivi.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieListAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        recyclerView = bind.recyclerView
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = MovieListAdapter(homeViewModel.homeMoviesList.value, homeViewModel)
        }
        adapter = recyclerView.adapter as MovieListAdapter

        homeViewModel.homeMoviesList.observe(viewLifecycleOwner) {
            adapter.updateFullData(it)
            if (it.isNotEmpty()) {
                bind.errorTextView.visibility = View.GONE
                bind.loadingBar.visibility = View.GONE
            } else {
                bind.errorTextView.visibility = View.VISIBLE
            }
        }

        homeViewModel.favouriteMoviesList.observe(viewLifecycleOwner) {
            val differenceList = homeViewModel.returnFavouritesDifference()
            if (differenceList != null) {
                homeViewModel.homeMoviesList.value?.forEachIndexed { index, item ->
                    if (item in differenceList) {
                        adapter.notifyItemChanged(index, "favouriteChange")
                    }
                }
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