package com.intive.patronage22.intivi

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.intive.patronage22.intivi.adapter.FragmentsAdapter
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.api.SessionManager
import com.intive.patronage22.intivi.database.UserRepository
import com.intive.patronage22.intivi.databinding.ActivityLoginBinding
import com.intive.patronage22.intivi.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var bind: ActivityLoginBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private var userRepo = UserRepository()
    lateinit var sessionManager: SessionManager
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        val view: View = bind.root
        setContentView(view)

        //TODO Dagger dependency injection (Room database no longer used)
        userRepo.initialize(this)
        loginViewModel.userRepo = userRepo

        ApiClient().initialize(application)
        sessionManager = SessionManager(applicationContext)

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

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        loginViewModel.logInEvent.observe(this) {
            if (it.canLogIn && it.token != null) {
                sessionManager.saveAuthToken(it.token!!)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        loginViewModel.loadingStatus.observe(this) {
            if (it) {
                bind.loadingIndicatorLayout?.visibility = View.VISIBLE
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND
                )
            } else {
                bind.loadingIndicatorLayout?.visibility = View.GONE
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            }
        }
    }
}