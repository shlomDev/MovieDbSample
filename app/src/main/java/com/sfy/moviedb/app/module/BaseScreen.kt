package com.sfy.moviedb.app.module

import android.support.annotation.LayoutRes

interface BaseScreen<T : BaseViewModel> {

    @LayoutRes
    fun getLayoutResource(): Int

    fun supplyViewModel(): T

    fun setupViews()

    fun bindViewModel()

}