package com.sfy.moviedb.app.module

import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sfy.moviedb.util.bind
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseFragment<T : BaseViewModel> : DaggerFragment(), BaseScreen<T> {


    private val compositeDisposable = CompositeDisposable()

    val baseActivity by lazy { activity as? BaseActivity<*> }

    var firstLoad = true
        private set

    val viewModel: T by lazy {
        supplyViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        bindViewModel()
    }

    override fun onDestroyView() {
        firstLoad = false
        viewModel.showLoader(false)
        compositeDisposable.clear()
        super.onDestroyView()
    }

    @CallSuper
    override fun bindViewModel() {
        val baseActivity = activity as? BaseActivity<*> ?: return
        if (viewModel == baseActivity.viewModel) return

        viewModel.mLoadingIndicator.bind(baseActivity.viewModel.mLoadingIndicator).addDisposable()
    }


    fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

}