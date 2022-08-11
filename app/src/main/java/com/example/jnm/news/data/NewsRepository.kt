package com.example.jnm.news.data

import android.util.Log
import com.example.jnm.news.data.remote.NewsService
import com.example.jnm.news.domain.INewsRepository
import com.example.jnm.news.presentation.model.NewsModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(private val service: NewsService) :
    INewsRepository {

    override suspend fun getNews(): Flow<List<NewsModelResponse>> {
        return flow<List<NewsModelResponse>> {
            try {
                val call = service.getNews()
                if (call.isNotEmpty()) {
                    emit(call)
                } else emit(emptyList())

            } catch (e: Exception) {
                Log.e("handle error",e.toString())
            }

        }.flowOn(Dispatchers.IO)
    }
}