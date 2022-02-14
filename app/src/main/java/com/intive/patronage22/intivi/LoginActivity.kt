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
        val editText = findViewById<EditText>(R.id.loginEditText)
        if (editText.text.isEmpty()) {
            editText.error = ""
            return false
        } else {
            return true
        }
    }

    private fun isPasswordOK(): Boolean {
        val editText = findViewById<EditText>(R.id.passwordEditText)
        if (editText.text.length < 5) {
            editText.error = ""
            return false
        } else {
            return true
        }
    }
}