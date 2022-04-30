package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding
import com.intive.patronage22.intivi.viewmodel.HomeViewModel
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        bind.homeViewModel = homeViewModel
        bind.lifecycleOwner = viewLifecycleOwner
        bind.executePendingBindings()
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.popularMoviesList.observe(viewLifecycleOwner, Observer {
            bind.recyclerView.apply {
                layoutManager = GridLayoutManager(activity, 2)
                if (homeViewModel.popularMoviesList.value != null) {
                    adapter = MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
                }
            }
        })

        //TODO update individual items instead of the whole dataset
        homeViewModel.popularMoviesList.observe(viewLifecycleOwner) {
            bind.recyclerView.adapter =
                MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
        }

        homeViewModel.favouriteMoviesList.observe(viewLifecycleOwner) {
            if (homeViewModel.popularMoviesList.value != null) {
                bind.recyclerView.adapter =
                    MovieListAdapter(homeViewModel.popularMoviesList.value!!, homeViewModel)
            }
        }
    }

    override fun onResume(){
        super.onResume()
        homeViewModel.fetchFavourites()
    }
}