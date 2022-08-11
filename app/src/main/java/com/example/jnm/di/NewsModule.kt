package com.example.jnm.di

import com.example.jnm.news.data.remote.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    @Singleton
    @Provides
    fun getApiServiceInstance(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }


    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}



