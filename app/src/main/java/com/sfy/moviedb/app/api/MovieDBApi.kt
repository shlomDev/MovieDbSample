package com.sfy.moviedb.app.api

import com.sfy.moviedb.data.MovieDbApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBApi {

    // https://api.themoviedb.org/3/discover/movie?api_key=884a40edb2411b090e7ef0cb36bef09c
    // &language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1

    @GET("discover/movie")
    fun getMovies(@Query("api_key") action: String): Single<MovieDbApiResponse>
}