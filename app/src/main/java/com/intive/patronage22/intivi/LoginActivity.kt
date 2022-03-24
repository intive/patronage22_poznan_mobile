package com.intive.patronage22.intivi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.intive.patronage22.intivi.Adapter.FragmentsAdapter

class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tabLayout = findViewById(R.id.loginTabLayout)
        viewPager = findViewById(R.id.loginViewPager)
        viewPager.adapter = FragmentsAdapter(this, tabLayout.tabCount)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setText(R.string.signIn)
                }
                1 -> {
                    tab.setText(R.string.signUp)
                }
            }
        }.attach()
    }
}