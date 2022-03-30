package com.intive.patronage22.intivi.model

var movieList = arrayListOf<MovieItem>()

class MovieItem(
    val title: String,
    val image: Int
) {
    val movieId:Long = movieList.size.toLong()
}