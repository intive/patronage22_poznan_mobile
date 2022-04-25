package com.intive.patronage22.intivi.model

data class FavouriteMovieResponse(
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: BelongsToCollection? = null,
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long,
    val images: FavouritesImages
)

data class BelongsToCollection(
    val id: Long,
    val name: String,
    val poster_path: String,
    val backdrop_path: String
)

data class Genre(
    val id: Long,
    val name: String
)

data class FavouritesImages(
    val poster: Backdrop,
    val backdrop: Backdrop
)

data class Backdrop(
    val xxs: String,
    val xs: String,
    val s: String,
    val m: String,
    val l: String,
    val xl: String,
    val original: String
)

data class ProductionCompany(
    val id: Long,
    val logo_path: String? = null,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)