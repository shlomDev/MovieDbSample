package com.sfy.moviedb.app.dagger.module

import com.google.gson.GsonBuilder
import com.sfy.moviedb.app.api.APIConfiguration
import com.sfy.moviedb.app.api.MovieDBApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * ONE of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 * [ ].
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIConfiguration.API_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create())
    }

    @Provides
    fun providesMovieDBApi(retrofit: Retrofit): MovieDBApi {
        return retrofit.create(MovieDBApi::class.java)
    }

}

