package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.intive.patronage22.intivi.Adapter.MovieListAdapter
import com.intive.patronage22.intivi.DetailsActivity
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.ViewModels.HomeViewModel
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentHomeBinding.inflate(inflater, container, false)

        bind.filterButtonMovies.setOnClickListener{
            bind.recyclerView.apply {
                layoutManager = GridLayoutManager(activity,2)
                adapter = MovieListAdapter(homeViewModel.moviesItemsFilter())
            }
        }

        bind.filterButtonSeries.setOnClickListener{
            bind.recyclerView.apply {
                layoutManager = GridLayoutManager(activity,2)
                adapter = MovieListAdapter(homeViewModel.seriesCardFilter())
            }
        }

        bind.filterButtonKids.setOnClickListener{
            bind.recyclerView.apply {
                layoutManager = GridLayoutManager(activity,2)
                adapter = MovieListAdapter(homeViewModel.kidsCardFilter())
            }
        }

        val actFooterTab = activity?.findViewById<TabLayout>(R.id.footer_tab)
        actFooterTab?.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
                bind.recyclerView.apply {
                    layoutManager = GridLayoutManager(activity,2)
                    adapter = MovieListAdapter(homeViewModel.homeItemsList)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                bind.recyclerView.apply {
                    layoutManager = GridLayoutManager(activity,2)
                    adapter = MovieListAdapter(homeViewModel.homeItemsList)
                }
            }
        })
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickNavigate(view.findViewById<ImageView>(R.id.app_logo), DetailsActivity::class.java)

        bind.recyclerView.apply {
            layoutManager = GridLayoutManager(activity,2)
            homeViewModel.createHomeItems()
            adapter = MovieListAdapter(homeViewModel.homeItemsList)
        }
    }

    private fun clickNavigate(view: View, activity: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(getActivity(), activity))
        }
    }
}