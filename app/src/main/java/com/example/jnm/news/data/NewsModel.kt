package com.example.jnm.news.presentation.model

data class NewsModelResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)