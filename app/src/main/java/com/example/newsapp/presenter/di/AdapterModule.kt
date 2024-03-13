package com.example.newsapp.presenter.di

import com.example.newsapp.presenter.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}