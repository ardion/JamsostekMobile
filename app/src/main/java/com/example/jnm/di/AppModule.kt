package com.example.jnm.di

import com.example.jnm.news.data.NewsRepository
import com.example.jnm.news.domain.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule{
    @Binds
    abstract fun provideNewsRepository(newsRepository: NewsRepository):INewsRepository
}
