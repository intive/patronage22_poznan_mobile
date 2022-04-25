package com.intive.patronage22.intivi.model

import android.util.Log

object MovieResponseParser {

    fun parseMovieResponseToMovieItem(responseItem: FavouriteMovieResponse): MovieItem {
        return MovieItem(
            responseItem.id,
            responseItem.overview,
            responseItem.releaseDate,
            responseItem.title,
            responseItem.voteAverage,
            responseItem.images.poster.original,
            responseItem.images.poster.xl
        )
    }

    fun parseMovieResponseToMovieItem(responseItem: MovieResponse): MovieItem {
        return MovieItem(
            responseItem.id,
            responseItem.overview,
            responseItem.releaseDate,
            responseItem.title,
            responseItem.voteAverage,
            responseItem.images.poster.original,
            responseItem.images.poster.xl
        )
    }

    fun parseMovieResponseToMovieItem(responseItemList: List<MovieResponse>): List<MovieItem> {
        val result = mutableListOf<MovieItem>()
        for (item in responseItemList){
            result.add(parseMovieResponseToMovieItem(item))
        }
        return result
    }

    @JvmName("parseMovieResponseToMovieItem1")
    fun parseMovieResponseToMovieItem(responseItemList: List<FavouriteMovieResponse>): List<MovieItem> {
        val result = mutableListOf<MovieItem>()
        for (item in responseItemList){
            result.add(parseMovieResponseToMovieItem(item))
        }
        return result
    }

}