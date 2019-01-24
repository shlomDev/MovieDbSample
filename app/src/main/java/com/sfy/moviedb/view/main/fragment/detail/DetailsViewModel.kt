package com.sfy.moviedb.view.main.fragment.detail

import com.sfy.moviedb.app.api.MovieDBRepository
import com.sfy.moviedb.app.module.BaseViewModel
import com.sfy.moviedb.data.Movie
import io.reactivex.subjects.PublishSubject


class DetailsViewModel(private val repository: MovieDBRepository): BaseViewModel() {

        val loadContent: PublishSubject<Movie> = PublishSubject.create()

    fun showdMovieInfo(movie: Movie){
        loadContent.onNext(movie)
    }
}