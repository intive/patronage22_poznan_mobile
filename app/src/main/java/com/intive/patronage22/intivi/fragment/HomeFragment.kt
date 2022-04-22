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