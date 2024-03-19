package com.example.newsapp.data.repository.dataSource

import com.example.newsapp.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
}