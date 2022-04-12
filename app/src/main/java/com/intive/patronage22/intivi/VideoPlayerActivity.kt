package com.intive.patronage22.intivi

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.intive.patronage22.intivi.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding
    private lateinit var controller: WindowInsetsControllerCompat
    val api_key =  "AIzaSyAEYViv8-o1hE3p5mbBLwI-bELPFL9UYLc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ytPlayer = binding.ytPlayer

        ytPlayer.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                player?.loadVideo("ndl1W4ltcmg") //Witcher main trailer
                player?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.d("Exception Log", "Video player Failed")
            }
        })

    }

    public override fun onPause() {
        super.onPause()
        controller.show(WindowInsetsCompat.Type.systemBars())
    }
}