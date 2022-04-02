package com.intive.patronage22.intivi

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        findViewById<RelativeLayout>(R.id.toolbarCircleBack).setOnClickListener {
            onBackPressed()
        }

        findViewById<Button>(R.id.watchButton).setOnClickListener{
            startActivity(Intent(this, VideoPlayerActivity::class.java))
        }

        if (Build.VERSION.SDK_INT < 29) this.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}