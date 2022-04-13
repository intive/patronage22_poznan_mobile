package com.intive.patronage22.intivi.API

import com.intive.patronage22.intivi.model.GenresResponse
import com.intive.patronage22.intivi.model.SignInResponse
import com.intive.patronage22.intivi.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST(Constants.SIGNUP_URL)
    fun signUp(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<SignUpResponse>

    @FormUrlEncoded
    @POST(Constants.SIGNIN_URL)
    fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<SignInResponse>

    @GET(Constants.GENRES_URL)
    fun fetchGenres(): Call<GenresResponse>
}
