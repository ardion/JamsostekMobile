package com.example.jnm.news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.jnm.R
import com.example.jnm.databinding.ActivityNewsBinding
import com.example.jnm.news.presentation.content.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityNewsBinding

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNewsBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        viewBinding.rvNews.adapter = NewsAdapter()
        viewModel.news.observe(this, {
            (viewBinding.rvNews.adapter as NewsAdapter).addList(it)
        })
    }
}