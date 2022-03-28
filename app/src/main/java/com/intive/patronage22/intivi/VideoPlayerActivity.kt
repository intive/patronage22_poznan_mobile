package com.intive.patronage22.intivi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate

class VideoPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getApplication().setTheme(R.style.LightTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        findViewById<ImageView>(R.id.close_video).setOnClickListener{
            finish()
        }

    }
}