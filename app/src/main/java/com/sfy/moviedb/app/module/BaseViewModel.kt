package com.sfy.moviedb.app.module

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val mLoadingIndicator = PublishSubject.create<Boolean>()
    val mErrorMessages = PublishSubject.create<String>()

    open fun start() = Unit

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun showLoader(show:Boolean) {
        mLoadingIndicator.onNext(show)
    }

    fun showErrorMessage(message: String) {
        mErrorMessages.onNext(message)
    }

    fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }
}