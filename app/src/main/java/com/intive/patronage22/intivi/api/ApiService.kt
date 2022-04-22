package com.intive.patronage22.intivi.api

import com.intive.patronage22.intivi.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST(Constants.SIGNUP_URL)
    fun signUp(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SignUpResponse>

    @FormUrlEncoded
    @POST(Constants.SIGNIN_URL)
    fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SignInResponse>

    @GET(Constants.GENRES_URL)
    fun fetchGenres(): Call<GenresResponse>

    @GET(Constants.POPULAR_URL)
    fun fetchPopular(): Call<List<Movie>>

    @GET(Constants.FAVOURITES_URL)
    fun fetchFavourites(): Call<List<FavouriteMovie>>

    @PUT("/api/mylist/{movieID}")
    fun putFavourites(@Path("movieID") movieID: Int): Call<Unit>

    @DELETE("/api/mylist/{movieID}")
    fun deleteFavourites(@Path("movieID") movieID: Int): Call<Unit>

    @GET("/api/movies/{movieID}")
    fun getMovieDetails(@Path("movieID") movieID: Int): Call<Movie>
}
