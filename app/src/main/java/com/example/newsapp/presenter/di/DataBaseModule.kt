package com.example.newsapp.presenter.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.db.ArticleDAO
import com.example.newsapp.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): ArticleDatabase {
        return Room.databaseBuilder(app, ArticleDatabase::class.java, "news_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(articleDatabase: ArticleDatabase): ArticleDAO {
        return articleDatabase.getArticleDAO()
    }
}