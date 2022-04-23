package com.intive.patronage22.intivi

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.intive.patronage22.intivi.databinding.ActivityDetailsBinding
import com.intive.patronage22.intivi.viewmodel.DetailsViewModel
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
            onBackPressed()
        }

        bind.watchButton.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("movieTitle", detailsViewModel.movieDetails.value?.title)
            startActivity(intent)
        }

        bind.toolbarCircleFavourite.setOnClickListener {
            if (detailsViewModel.isFavourite.value == true) {
                detailsViewModel.deleteFavourite()
            } else detailsViewModel.putFavourite()
        }

        val movieId = bundle?.getInt("movieId")
        detailsViewModel.getMovieDetails(movieId!!)

        detailsViewModel.movieDetails.observe(this) {
            if (detailsViewModel.movieDetails.value != null) {
                val details = detailsViewModel.movieDetails.value!!
                Picasso.get().load(details.posterOriginalUrl).error(R.drawable.app_logo)
                    .into(bind.detailsPhoto)
                bind.detailsTitle.text = details.title
                bind.detailsDescriptionText.text = details.overview
                bind.detailsYearText.text = details.releaseDate.substringBefore("-", "N/A")
                bind.detailsPGText.text = details.voteAverage.toString()
                bind.detailsSeasonsText.text = getString(R.string.movie)
            }
        }

        detailsViewModel.apiError.observe(this) {
            if (it != null) {
                bind.errorText?.text = it
                bind.errorText?.visibility = View.VISIBLE
            } else bind.errorText?.visibility = View.GONE
        }

        detailsViewModel.apiErrorFavouriteOperation.observe(this) {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        detailsViewModel.isFavourite.observe(this) {
            if (it) {
                bind.imageViewHeart.setBackgroundResource(R.drawable.ic_favourite_grid_item_fill)
            } else {
                bind.imageViewHeart.setBackgroundResource(R.drawable.ic_favourite_grid_item)
            }
        }
    }

    //TODO temp solution to refresh favourited status displayed in MainActivity which could have changed in details activity. Should be individual data update??
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}