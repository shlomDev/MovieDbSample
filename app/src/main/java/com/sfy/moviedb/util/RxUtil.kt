package com.sfy.moviedb.util

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.CompletableSubject
import io.reactivex.subjects.PublishSubject

/**
 * Throws an [IllegalStateException] if the [value] is false.
 **/
fun checkTrue(b: Boolean?): Completable {
    return if (b != true) Completable.error(IllegalStateException("The boolean expression is false"))
    else Completable.complete()
}

fun checkEmptyList(b: List<Any>?): Single<List<Any>> {
    return if (b.isNotNullOrEmpty()) Single.just(b!!) else Single.error(IllegalStateException("The list is null or empty"))
}

fun List<Any>?.isNotNullOrEmpty(): Boolean = this?.isNotEmpty() == true

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

fun <T> PublishSubject<T>.bind(subject: PublishSubject<T>): Disposable {
    return subscribe({ subject.onNext(it) }, { throwable -> subject.onError(throwable) })
}

fun <T> BehaviorSubject<T>.bind(subject: BehaviorSubject<T>): Disposable {
    return subscribe({ subject.onNext(it) }, { throwable -> subject.onError(throwable) })
}

fun CompletableSubject.bind(subject: CompletableSubject): Disposable {
    return subscribe({ subject.onComplete() }, { throwable -> subject.onError(throwable) })
}

fun <T> PublishProcessor<T>.bind(subject: PublishProcessor<T>): Disposable {
    return subscribe({ subject.onNext(it) }, { throwable -> subject.onError(throwable) })
}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

fun <T> Observable<T>.observeOnMainThread(): Observable<T> = observeOn(AndroidSchedulers.mainThread())
fun Completable.observeOnMainThread(): Completable = observeOn(AndroidSchedulers.mainThread())
fun <T> Single<T>.observeOnMainThread(): Single<T> = observeOn(AndroidSchedulers.mainThread())
fun <T> Flowable<T>.observeOnMainThread(): Flowable<T> = observeOn(AndroidSchedulers.mainThread())

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

fun <T> BehaviorSubject<T>.valueOrError(): Single<T> {
    return if (hasValue()) Single.just(value)
    else Single.error(NoSuchElementException())
}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

fun <T> PublishSubject<T>.onSafeNullNext(item: T?) : Boolean {
     item?.also { onNext(it) } ?: return false
    return true
}

fun <T> BehaviorSubject<T>.onSafeNullNext(item: T?) : Boolean {
    item?.also { onNext(it) }?: return false
    return true
}

