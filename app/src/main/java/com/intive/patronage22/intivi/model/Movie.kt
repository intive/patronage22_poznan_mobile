package com.intive.patronage22.intivi.model

data class Movie (
    val adult: Boolean,
    val genreIDS: List<Long>,
    val id: Long,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long,
    val images: Images
)

data class Images (
    val poster: ImageUrls,
    val backdrop: ImageUrls
)

data class ImageUrls (
    val xxs: String,
    val xs: String,
    val s: String,
    val m: String,
    val l: String,
    val xl: String,
    val original: String
)