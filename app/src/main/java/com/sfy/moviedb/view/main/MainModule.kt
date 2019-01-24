package com.sfy.moviedb.view.main

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

}
