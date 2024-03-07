package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.ApiResponse
import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(): Resource<ApiResponse> {
        return newsRepository.getNewsHeadlines()
    }
}