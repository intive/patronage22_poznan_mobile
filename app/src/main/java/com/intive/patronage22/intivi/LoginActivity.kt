package com.intive.patronage22.intivi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button



class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.loginButton).setOnClickListener(listener)
    }
    private val listener = View.OnClickListener { view ->
        when (view.id) {
            R.id.loginButton -> {
                startActivity( Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

}
