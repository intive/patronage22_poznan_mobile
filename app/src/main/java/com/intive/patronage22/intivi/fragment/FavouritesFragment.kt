package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.adapter.FavouritesListAdapter
import com.intive.patronage22.intivi.databinding.FragmentFavouritesBinding
import com.intive.patronage22.intivi.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
            layoutManager = GridLayoutManager(activity,2)
            if(homeViewModel.favouriteMoviesList.value != null) {
                adapter = FavouritesListAdapter(homeViewModel.favouriteMoviesList.value!!, homeViewModel)
            }
        }

        homeViewModel.favouriteMoviesList.observe(viewLifecycleOwner) {
            bind.recyclerView.adapter = FavouritesListAdapter(homeViewModel.favouriteMoviesList.value!!, homeViewModel)
            if(it.isNotEmpty()) {
                bind.favouritesTextMessage.visibility = View.GONE
            } else {
                bind.favouritesTextMessage.visibility = View.VISIBLE
            }
        }

        waitForMovies()

    }

    private fun waitForMovies(){
        lifecycleScope.launch{
            delay(15000)
            bind.favouritesTextMessage.text = getString(R.string.loading_favourites_error)
        }
    }
}