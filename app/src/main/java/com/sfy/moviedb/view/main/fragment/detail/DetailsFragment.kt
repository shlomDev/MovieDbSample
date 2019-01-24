package com.sfy.moviedb.view.main.fragment.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.sfy.moviedb.app.module.BaseFragment
import com.sfy.moviedb.view.main.MainActivity
import javax.inject.Inject
import com.sfy.moviedb.R
import com.sfy.moviedb.app.api.APIConfiguration
import com.sfy.moviedb.data.Movie
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : BaseFragment<DetailsViewModel>(){
    val mainActivity by lazy { activity as? MainActivity }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayoutResource(): Int = R.layout.fragment_details

    override fun supplyViewModel() = ViewModelProviders.of(activity!!, viewModelFactory).get(DetailsViewModel::class.java)

    override fun onStart() {
        super.onStart()
        viewModel.showdMovieInfo(mainActivity?.selectedMovie!!)
    }

    override fun setupViews() {

    }

    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.loadContent
            .subscribe{populateData(it)}
            .addDisposable()

        mainActivity!!.viewModel.navigateToDiscover
            .subscribe { mainActivity!!.replaceFragment(DetailsFragment(), false) }
            .addDisposable()
    }

    fun populateData(movie: Movie){
        mTitle.text = movie.title
        Glide.with(this).load(APIConfiguration.API_IMAGE_URL+movie.backdropPath).into(img)
        description.text = movie.overview
        year.text = movie.releaseDate
        rating.text = movie.voteAverage.toString()+"/10"
    }

}