package com.example.jnm.news.domain

import com.example.jnm.news.presentation.model.NewsModelResponse
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    suspend fun getNews(): Flow<List<NewsModelResponse>>
}