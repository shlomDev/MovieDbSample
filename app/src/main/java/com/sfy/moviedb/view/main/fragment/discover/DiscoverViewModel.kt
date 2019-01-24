package com.sfy.moviedb.view.main.fragment.discover

import com.sfy.moviedb.app.api.MovieDBRepository
import com.sfy.moviedb.app.module.BaseViewModel
import com.sfy.moviedb.data.Movie
import io.reactivex.subjects.PublishSubject

class DiscoverViewModel(private val repository: MovieDBRepository) : BaseViewModel() {

    val mLoading: PublishSubject<Boolean> = PublishSubject.create()
    val movieList: PublishSubject<ArrayList<Movie>> = PublishSubject.create()

    fun getMovieList() {
        repository.getMovieList()
            .doOnSuccess { mLoading.onNext(true) }
            .doFinally { mLoading.onNext(false) }
            .subscribe({ movieList.onNext(it.movies!!) }, {})

    }
}