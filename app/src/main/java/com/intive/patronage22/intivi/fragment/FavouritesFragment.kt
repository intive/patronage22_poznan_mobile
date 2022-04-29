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
import com.intive.patronage22.intivi.model.MovieItem
import com.intive.patronage22.intivi.viewmodel.HomeViewModel

class FavouritesFragment : Fragment() {

    private lateinit var bind: FragmentFavouritesBinding
    private lateinit var recyclerView: RecyclerView
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var previousItemList: List<MovieItem>? = null

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

        homeViewModel.favouriteMoviesList.observe(viewLifecycleOwner) {
            when (it.isNotEmpty()) {
                true -> bind.loadingBar.visibility = View.GONE
                false -> bind.errorTextView.visibility = View.VISIBLE
            }
            val oldAdapterData = (recyclerView.adapter as MovieListAdapter).data?.toList()
            oldAdapterData?.forEachIndexed { index, item ->
                if (item !in homeViewModel.favouriteMoviesList.value!!) {
                    (recyclerView.adapter as MovieListAdapter).removeItemAt(index)
                }
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

    //TODO use notify item inserted instead
    override fun onResume() {
        super.onResume()
        homeViewModel.fetchFavourites()
        if(previousItemList != homeViewModel.favouriteMoviesList.value) {
            recyclerView.adapter =
                MovieListAdapter(homeViewModel.favouriteMoviesList.value!!, homeViewModel)
        }
    }

    override fun onPause(){
        super.onPause()
        previousItemList = (recyclerView.adapter as MovieListAdapter).data!!.toList()
    }

}