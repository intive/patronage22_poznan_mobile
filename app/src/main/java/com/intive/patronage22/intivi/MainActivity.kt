package com.intive.patronage22.intivi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickNavigate(findViewById<ImageView>(R.id.imageView), DetailsActivity::class.java)

        /*
        findViewById<ImageView>(R.id.imageView).setOnClickListener{
            startActivity(Intent(this, DetailsActivity::class.java))
        }
        */
    }

    private fun clickNavigate(view: View, activity: Class<*>){
        Log.d("logos", "View: $view, Activity: $activity" )
        view.setOnClickListener{
            Log.d("logos", "startingActivity: $activity")
            startActivity(Intent(this, activity))
        }
    }
}