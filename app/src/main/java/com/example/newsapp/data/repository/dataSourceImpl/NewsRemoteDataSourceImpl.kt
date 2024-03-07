package com.example.newsapp.data.repository.dataSourceImpl

import com.example.newsapp.data.api.NewsAPIService
import com.example.newsapp.data.model.APIResponse
import com.example.newsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
    private val country: String,
    private val page: Int
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(): Response<APIResponse> {
        return newsAPIService.getTopHeadLines(country, page)
    }
}