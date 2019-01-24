package com.sfy.moviedb.app.dagger.module


import com.sfy.moviedb.app.dagger.scope.FragmentScoped
import com.sfy.moviedb.view.main.MainModule
import com.sfy.moviedb.view.main.fragment.detail.DetailsFragment
import com.sfy.moviedb.view.main.fragment.discover.DiscoverFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module FragmentBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent
 * that it is going to have all these subcomponents nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules
 * and be aware of a scope annotation @FragmentScoped When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */

@Module
abstract class FragmentBindingModule {


    @FragmentScoped
    @ContributesAndroidInjector(modules = [(MainModule::class)])
    internal abstract fun discoverFragment(): DiscoverFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [(MainModule::class)])
    internal abstract fun detailFragment(): DetailsFragment

}

