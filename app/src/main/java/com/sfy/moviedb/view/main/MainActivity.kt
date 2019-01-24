package com.sfy.moviedb.view.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.sfy.moviedb.R
import com.sfy.moviedb.app.module.BaseActivity
import com.sfy.moviedb.data.Movie
import com.sfy.moviedb.extension.replaceFragmentInActivity
import com.sfy.moviedb.view.main.fragment.detail.DetailsFragment
import com.sfy.moviedb.view.main.fragment.discover.DiscoverFragment
import com.sfy.moviedb.widget.animation.FragmentAnimationType
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<MainViewModel>() {

    lateinit var selectedMovie: Movie

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            replaceFragment(DiscoverFragment(), false)
        }
    }
    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun supplyViewModel() = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)


    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.navigateToDetails
            .subscribe{ setToolbar(getString(R.string.movie_details), true)
                    replaceFragment(DetailsFragment(), false)}
            .addDisposable()

        viewModel.navigateToDiscover
            .subscribe{setToolbar(getString(R.string.popular_movies), false)
                replaceFragment(DiscoverFragment(), true)}
            .addDisposable()

    }

    override fun setupViews() {
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
    }


    fun setToolbar(title: String, showArrow: Boolean){
        toolbar.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(showArrow)
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        replaceFragmentInActivity(fragment, R.id.fragment_container, addToBackStack, fragmentAnimationType = FragmentAnimationType.FADE)
    }

    fun selectedMovie(movie: Movie) {
        selectedMovie = movie
    }
}
