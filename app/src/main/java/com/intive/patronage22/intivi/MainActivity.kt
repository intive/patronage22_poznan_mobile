package com.intive.patronage22.intivi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickNavigate(findViewById<ImageView>(R.id.imageView), DetailsActivity::class.java)
    }

    private fun clickNavigate(view: View, activity: Class<*>){
        view.setOnClickListener{
            startActivity(Intent(this, activity))
        }
    }
}