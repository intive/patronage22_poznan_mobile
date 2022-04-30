package com.intive.patronage22.intivi.api

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.intive.patronage22.intivi.R

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?){
    Glide.with(view)
        .load(url)
        .error(R.drawable.app_logo)
        .into(view)
}

@BindingAdapter("image_url")
fun loadImage(view: ImageView, url: MutableLiveData<String>){
    Glide.with(view)
        .load(url.value)
       // .error(R.drawable.app_logo)
        .into(view)
}