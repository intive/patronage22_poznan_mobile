package com.intive.patronage22.intivi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private var isLoginFormValid: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            isLoginOK()
            isPasswordOK()
            isLoginFormValid = isLoginOK() && isPasswordOK()
        }
    }

    private fun isLoginOK(): Boolean {
        if (findViewById<EditText>(R.id.loginEditText).text.isEmpty()) {
            findViewById<EditText>(R.id.loginEditText).error = ""
            return false
        } else {
            return true
        }
    }

    private fun isPasswordOK(): Boolean {
        if (findViewById<EditText>(R.id.passwordEditText).text.length < 5) {
            findViewById<EditText>(R.id.passwordEditText).error = ""
            return false
        } else {
            return true
        }
    }
}