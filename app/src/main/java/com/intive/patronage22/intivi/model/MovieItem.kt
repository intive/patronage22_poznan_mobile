package com.intive.patronage22.intivi.model

data class MovieItem(
    val title: String,
    val image: Int
) {
    val movieId: Long = currentId++

    companion object {
        // todo: this needs to be definitely fixed
        var currentId: Long = 0
    }
}