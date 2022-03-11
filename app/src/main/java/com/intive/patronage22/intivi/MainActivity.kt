package com.intive.patronage22.intivi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.intive.patronage22.intivi.Adapter.MainFragmentsAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.footer_tab)
        viewPager = findViewById(R.id.main_viewPager)
        Log.d("bayraktar", "wtf is this")

        val adapter = MainFragmentsAdapter(this, tabLayout.tabCount)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setText(R.string.home)
                    tab.setIcon(R.drawable.ic_home)
                    Log.d("bayraktar", "Home selected")
                }
                1 -> {
                    tab.setText(R.string.favourites)
                    tab.setIcon(R.drawable.ic_favourite)
                    Log.d("bayraktar", "Favourites selected")
                }
                2 -> {
                    tab.setText(R.string.genres)
                    tab.setIcon(R.drawable.ic_genres)
                }
            }
        }.attach()
    }
}