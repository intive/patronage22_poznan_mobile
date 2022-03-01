package com.intive.patronage22.intivi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.widget.EditText
import android.widget.ImageView

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        findViewById<ImageView>(R.id.imageViewResetPasswordBack).setOnClickListener {
            onBackPressed()
        }

        /*
        val showPassword = findViewById<ImageView>(R.id.imageViewShowPassword)
        val editTextResetPassword = findViewById<EditText>(R.id.editTextResetPassword)

        showPassword.setOnClickListener {
            editTextResetPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }

         */
    }
}