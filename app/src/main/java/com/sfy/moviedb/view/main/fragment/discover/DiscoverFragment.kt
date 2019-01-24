package com.sfy.moviedb.view.main.fragment.discover

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.sfy.moviedb.app.module.BaseFragment
import com.sfy.moviedb.data.Movie
import com.sfy.moviedb.view.main.MainActivity
import com.sfy.moviedb.view.main.fragment.DiscoverAdapter
import com.sfy.moviedb.view.main.fragment.detail.DetailsFragment
import kotlinx.android.synthetic.main.fragment_discover.*
import javax.inject.Inject
import com.sfy.moviedb.R


class DiscoverFragment : BaseFragment<DiscoverViewModel>(){

    val mainActivity by lazy { activity as? MainActivity }
    private var discoverAdapter: DiscoverAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayoutResource(): Int = R.layout.fragment_discover

    override fun supplyViewModel() = ViewModelProviders.of(activity!!, viewModelFactory).get(DiscoverViewModel::class.java)

    override fun onStart() {
        super.onStart()
        viewModel.getMovieList()
    }

    override fun setupViews() {
        initRecyclerview()
    }

    fun initRecyclerview(){
        recyclerview_discover.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            discoverAdapter = DiscoverAdapter()
            adapter = discoverAdapter
        }
    }
    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.mLoading
            .subscribe {
                progressLoaderVisibility(it)
            }.addDisposable()

        viewModel.movieList
            .subscribe{discoverAdapter?.loadMovieslist(it)}
            .addDisposable()

        mainActivity?.viewModel!!.navigateToDetails
            .subscribe { mainActivity?.replaceFragment(DetailsFragment(), true) }
            .addDisposable()

        discoverAdapter?.onClickMovieItem?.subscribe { showDetailsFragment(it) }?.addDisposable()

    }

    private fun progressLoaderVisibility(show: Boolean){
        progress_bar.visibility = if(show) View.VISIBLE else View.GONE
    }

    private fun showDetailsFragment(movie: Movie) {
        mainActivity?.selectedMovie(movie)
        mainActivity?.viewModel!!.showDetailsFragment()
    }
}