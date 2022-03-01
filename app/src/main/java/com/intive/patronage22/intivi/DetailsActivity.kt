package com.intive.patronage22.intivi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        findViewById<RelativeLayout>(R.id.toolbarCircleBack).setOnClickListener {
            onBackPressed()
        }
    }
}