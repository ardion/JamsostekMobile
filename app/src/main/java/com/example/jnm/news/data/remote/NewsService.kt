package com.example.jnm.news.data.remote

import com.example.jnm.news.presentation.model.NewsModelResponse
import retrofit2.http.GET

interface NewsService {
    @GET("/posts")
    suspend fun getNews(): List<NewsModelResponse>
}