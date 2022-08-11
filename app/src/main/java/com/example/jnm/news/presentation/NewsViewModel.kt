package com.example.jnm.news.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jnm.news.domain.INewsRepository
import com.example.jnm.news.presentation.model.NewsModelResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: INewsRepository) : ViewModel() {
    private var _news = MutableLiveData<List<NewsModelResponse>>()
    val news: LiveData<List<NewsModelResponse>> = _news

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            repository.getNews().collect {
                _news.value=it
            }
        }
    }

}