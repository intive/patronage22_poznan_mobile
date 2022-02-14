package com.intive.patronage22.intivi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.view.View

class LoginActivity : AppCompatActivity() {

    private var isLoginFormValid: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        fun isLoginOK(): Boolean {
            val editText = findViewById<EditText>(R.id.loginEditText)
            if (editText.text.isEmpty()) {
                editText.error = ""
                return false
            } else {
                return true
            }
        }

        fun isPasswordOK(): Boolean {
            val editText = findViewById<EditText>(R.id.passwordEditText)
            if (editText.text.length < 5) {
                editText.error = ""
                return false
            } else {
                return true
            }
        }

        val listener = View.OnClickListener { view ->
            when (view.id) {
                R.id.loginButton -> {
                    isLoginFormValid = isLoginOK() and isPasswordOK()
                    if (isLoginFormValid == true) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
        findViewById<Button>(R.id.loginButton).setOnClickListener(listener)
    }
}
