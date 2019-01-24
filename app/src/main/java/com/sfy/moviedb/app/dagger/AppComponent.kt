package com.sfy.moviedb.app.dagger


import android.app.Application
import com.sfy.moviedb.app.App
import com.sfy.moviedb.app.dagger.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton



@Singleton
@Component(modules = [
    ApplicationModule::class,
    DataBindingModule::class,
    NetworkModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class,
    AndroidSupportInjectionModule::class])

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

