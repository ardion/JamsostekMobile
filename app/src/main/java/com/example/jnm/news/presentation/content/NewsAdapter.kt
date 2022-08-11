package com.example.jnm.news.presentation.content

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jnm.databinding.ItemNewsBinding
import com.example.jnm.news.presentation.model.NewsModelResponse
import com.squareup.picasso.Picasso


class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private var items = mutableListOf<NewsModelResponse>()

    fun addList(list: List<NewsModelResponse>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = items[position]
        with(holder) {
            binding.tvTitle.text = item.title
            binding.tvBody.text = item.body
        }
    }

    inner class NewsHolder(
        val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root)
}