package com.intive.patronage22.intivi

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        findViewById<ImageView>(R.id.imageViewResetPasswordBack).setOnClickListener {
            onBackPressed()
        }

        val eye = findViewById<ImageView>(R.id.imageViewShowPassword)
        val editTextResetPassword = findViewById<EditText>(R.id.editTextResetPassword)
        val show: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.ic_show_password, null)
        val hide: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.ic_hide_password, null)

        eye.setOnClickListener {
            if (eye.drawable.equals(show)) {
                editTextResetPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                eye.setImageDrawable(hide)
            } else {
                editTextResetPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                eye.setImageDrawable(show)
            }
        }
    }
}