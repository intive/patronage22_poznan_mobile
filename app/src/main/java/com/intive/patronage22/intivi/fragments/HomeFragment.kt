package com.intive.patronage22.intivi.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.DetailsActivity
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.viewmodels.HomeViewModel
import com.intive.patronage22.intivi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickNavigate(view.findViewById<ImageView>(R.id.app_logo), DetailsActivity::class.java)
        bind.recyclerView.adapter=MovieListAdapter()
        bind.recyclerView.layoutManager=GridLayoutManager(activity,2)
        homeViewModel.setData()
        (bind.recyclerView.adapter as MovieListAdapter).setMovieList(homeViewModel.movieList)

    }

    private fun clickNavigate(view: View, activity: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(getActivity(), activity))
        }
    }
}