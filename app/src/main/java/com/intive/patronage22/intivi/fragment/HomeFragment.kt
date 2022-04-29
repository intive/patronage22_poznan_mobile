package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding
import com.intive.patronage22.intivi.viewmodel.HomeViewModel

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
            adapter = MovieListAdapter(homeViewModel.homeMoviesList.value, homeViewModel)
        }

        homeViewModel.homeMoviesList.observe(viewLifecycleOwner) {
            bind.recyclerView.adapter =
                MovieListAdapter(homeViewModel.homeMoviesList.value!!, homeViewModel)
            if (it.isNotEmpty()) {
                bind.errorTextView.visibility = View.GONE
                bind.loadingBar.visibility = View.GONE
            } else {
                bind.errorTextView.visibility = View.VISIBLE
            }
        }

        //TODO notify favourited state only using payload
        homeViewModel.favouriteMoviesList.observe(viewLifecycleOwner) {
            val differenceList =
                homeViewModel.returnFavouritesDifference()
            if (differenceList != null) {
                homeViewModel.homeMoviesList.value?.forEachIndexed { index, item ->
                    if (item in differenceList) {
                        bind.recyclerView.adapter?.notifyItemChanged(index)
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
}