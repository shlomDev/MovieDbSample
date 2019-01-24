package com.sfy.moviedb.app

import com.sfy.moviedb.app.dagger.AppComponent
import com.sfy.moviedb.app.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class App : DaggerApplication() {
    var appComponent: AppComponent? = null

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        appComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }

}