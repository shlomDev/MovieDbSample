package com.sfy.moviedb.view.main

import com.sfy.moviedb.app.module.BaseViewModel
import io.reactivex.subjects.PublishSubject


class MainViewModel: BaseViewModel() {

    val navigateToDiscover: PublishSubject<Boolean> = PublishSubject.create()
    val navigateToDetails: PublishSubject<Boolean> = PublishSubject.create()

    override fun start() {
        navigateToDiscover.onNext(true)
    }

    fun showDetailsFragment(){
        navigateToDetails.onNext(true)
    }
}