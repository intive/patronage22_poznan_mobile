package com.intive.patronage22.intivi.model

data class MovieItem(
    val id: Int,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val posterOriginalUrl: String,
    val posterXlUrl: String
)
