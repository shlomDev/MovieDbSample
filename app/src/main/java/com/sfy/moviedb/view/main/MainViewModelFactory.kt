package com.sfy.moviedb.view.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sfy.moviedb.app.api.MovieDBRepository
import com.sfy.moviedb.view.main.fragment.detail.DetailsViewModel
import com.sfy.moviedb.view.main.fragment.discover.DiscoverViewModel
import javax.inject.Inject

class MainViewModelFactory
@Inject
constructor(private val repository: MovieDBRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel()
            modelClass.isAssignableFrom(DiscoverViewModel::class.java) ->
                DiscoverViewModel(repository)
            modelClass.isAssignableFrom(DetailsViewModel::class.java) ->
                DetailsViewModel(repository)
            else -> throw Throwable("Unknown ViewModel class")
        } as T
    }

}

