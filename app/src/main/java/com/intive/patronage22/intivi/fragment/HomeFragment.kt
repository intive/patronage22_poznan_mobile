package com.intive.patronage22.intivi.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.DetailsActivity
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.viewmodel.HomeViewModel
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding
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

//        bind.filterButtonMovies.setOnClickListener{
//            bind.recyclerView.apply {
//                layoutManager = GridLayoutManager(activity,2)
//                adapter = MovieListAdapter(homeViewModel.moviesItemsFilter())
//            }
//        }
//
//        bind.filterButtonSeries.setOnClickListener{
//            bind.recyclerView.apply {
//                layoutManager = GridLayoutManager(activity,2)
//                adapter = MovieListAdapter(homeViewModel.seriesCardFilter())
//            }
//        }
//
//        bind.filterButtonKids.setOnClickListener{
//            bind.recyclerView.apply {
//                layoutManager = GridLayoutManager(activity,2)
//                adapter = MovieListAdapter(homeViewModel.kidsCardFilter())
//            }
//        }

//        val actFooterTab = activity?.findViewById<TabLayout>(R.id.footer_tab)
//        actFooterTab?.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//            }
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                bind.recyclerView.apply {
//                    layoutManager = GridLayoutManager(activity,2)
//                    adapter = MovieListAdapter(homeViewModel.popularMoviesList.value!!)
//                }
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                bind.recyclerView.apply {
//                    layoutManager = GridLayoutManager(activity,2)
//                    adapter = MovieListAdapter(homeViewModel.popularMoviesList.value!!)
//                }
//            }
//        })

        homeViewModel.popularMoviesList.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                bind.recyclerView.adapter = MovieListAdapter(homeViewModel.popularMoviesList.value!!)
                bind.noMoviesErrorTextView.visibility = View.GONE
            }
        }

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickNavigate(view.findViewById<ImageView>(R.id.app_logo), DetailsActivity::class.java)

        bind.recyclerView.apply {
            layoutManager = GridLayoutManager(activity,2)
            //homeViewModel.createHomeItems()
            if(homeViewModel.popularMoviesList.value != null) {
                adapter = MovieListAdapter(homeViewModel.popularMoviesList.value!!)
            }
        }

        waitForMovies()

    }

    //TODO click on recycler view item (in onBindViewHolder) starts details activity with appropriate movie details.
    private fun clickNavigate(view: View, activity: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(getActivity(), activity))
        }
    }

    private fun waitForMovies(){
        lifecycleScope.launch{
            delay(15000)
            bind.noMoviesErrorTextView.text = getString(R.string.no_movies_error_message)
        }
    }
}