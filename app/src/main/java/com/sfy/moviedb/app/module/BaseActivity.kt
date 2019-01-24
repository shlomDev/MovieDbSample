package com.sfy.moviedb.app.module

import android.os.Bundle
import android.support.annotation.CallSuper
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseActivity<T : BaseViewModel> : DaggerAppCompatActivity(), BaseScreen<T> {

    private val compositeDisposable = CompositeDisposable()

    var onBackPressedListener: OnBackPressedListener? = null


    val viewModel: T by lazy {
        supplyViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        setupViews()

    }

    override fun onStart() {
        super.onStart()
        bindViewModel()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    @CallSuper
    override fun bindViewModel() {
    }

    fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun onBackPressed() {
        onBackPressedListener?.onBack() ?: super.onBackPressed()
    }

    interface OnBackPressedListener {
        fun onBack()
    }

}

