package com.sfy.moviedb.util


import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object MovieDBSchedulers {

}

fun <T> Observable<T>.applyAPI(): Observable<T> {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.applyAPI(): Single<T>{
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applyAPI(): Completable {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}




