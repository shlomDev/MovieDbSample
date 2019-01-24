package com.sfy.moviedb.app.api

import com.sfy.moviedb.data.MovieDbApiResponse
import com.sfy.moviedb.util.applyAPI
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieDBRepository
@Inject constructor(private val movieApiService: MovieDBApi) {
    fun getMovieList(): Single<MovieDbApiResponse> = movieApiService.getMovies(APIConfiguration.API_KEY).applyAPI()
}