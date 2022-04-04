package com.intive.patronage22.intivi

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        findViewById<ImageView>(R.id.imageViewResetPasswordBack).setOnClickListener {
            onBackPressed()
        }

        findViewById<Button>(R.id.buttonResetPassword).setOnClickListener {
            findViewById<TextView>(R.id.textViewResetPasswordMessage).visibility = View.VISIBLE
            Thread {
                Thread.sleep(5000)
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }.start()
        }

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}