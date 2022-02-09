package com.intive.patronage22.intivi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.loginButton)
        val loginEditText: EditText = findViewById(R.id.loginEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)

        loginButton.setOnClickListener {

            fun loginValidation(): Boolean {
                val isLoginOK: Boolean
                val isPasswordOK: Boolean

                if (loginEditText.text.isEmpty()) {
                    loginEditText.error = ""
                    isLoginOK = false
                } else {
                    isLoginOK = true
                }

                if (passwordEditText.text.length < 5) {
                    passwordEditText.error = ""
                    isPasswordOK = false
                } else {
                    isPasswordOK = true
                }

                return isLoginOK && isPasswordOK
            }

            loginValidation()
        }
    }
}