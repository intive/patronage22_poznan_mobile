package com.intive.patronage22.intivi

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.intive.patronage22.intivi.Adapter.FragmentsAdapter
import com.intive.patronage22.intivi.ViewModels.LoginViewModel
import com.intive.patronage22.intivi.database.UserRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private var userRepo = UserRepository()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userRepo.initialize(this)
        loginViewModel.userRepo = userRepo

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

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        loginViewModel.canLogIn.observe(this) {
            if(it) {
                try {
                    val loggedUserId  = loginViewModel.loggedUserId.value
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("EXTRA_USER_ID", loggedUserId)
                    startActivity(intent)
                    finish()
                } catch (e: Exception){
                    Log.e("Exception: ", e?.localizedMessage.toString())}
            }
        }
    }
}