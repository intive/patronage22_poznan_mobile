package com.intive.patronage22.intivi

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.intive.patronage22.intivi.adapter.MovieListAdapter
import com.intive.patronage22.intivi.databinding.ActivityDetailsBinding
import com.intive.patronage22.intivi.viewmodel.DetailsViewModel
import com.intive.patronage22.intivi.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    private lateinit var bind: ActivityDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        bind = ActivityDetailsBinding.inflate(layoutInflater)
        val view: View = bind.root
        setContentView(view)

        bind.toolbarCircleBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        bind.watchButton.setOnClickListener{
            startActivity(Intent(this, VideoPlayerActivity::class.java))
        }

        val movieId = bundle?.getInt("movieId")
        detailsViewModel.getMovieDetails(movieId!!)

        detailsViewModel.movieDetails.observe(this) {
            if (detailsViewModel.movieDetails.value != null) {
                val details = detailsViewModel.movieDetails.value!!
                Picasso.get().load(details.images.poster.original).error(R.drawable.app_logo).into(bind.detailsPhoto)
                bind.detailsTitle.text = details.title
                bind.detailsDescriptionText.text = details.overview
                bind.detailsYearText.text = details.releaseDate.substringBefore("-", "N/A")
                bind.detailsPGText.text = details.voteAverage.toString()
                bind.detailsSeasonsText.text = getString(R.string.movie)
            }
        }

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}