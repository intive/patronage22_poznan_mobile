package com.intive.patronage22.intivi

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.intive.patronage22.intivi.adapter.MainFragmentsAdapter
import com.intive.patronage22.intivi.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.footer_tab)
        viewPager = findViewById(R.id.main_viewPager)
        viewPager.adapter = MainFragmentsAdapter(this, tabLayout.tabCount)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setText(R.string.home)
                    tab.setIcon(R.drawable.ic_home)
                }
                1 -> {
                    tab.setText(R.string.favourites)
                    tab.setIcon(R.drawable.ic_favourite)
                }
                2 -> {
                    tab.setText(R.string.genres)
                    tab.setIcon(R.drawable.ic_genres)
                }
            }
        }.attach()

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        homeViewModel.openDetailsEvent.observe(this) {
            if (it.movieId != null) {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("movieId", it.movieId)
                startActivity(intent)
            }
        }

        homeViewModel.apiErrorFavouriteOperation.observe(this) {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}