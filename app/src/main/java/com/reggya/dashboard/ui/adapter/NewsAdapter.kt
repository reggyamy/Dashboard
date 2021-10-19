package com.reggya.dashboard.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.databinding.ContentNewsBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = ArrayList<BeritaItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<BeritaItem>?){
        if(newData == null) return
            news.clear()
            news.addAll(newData)
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ContentNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = news[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = news.size

    class ViewHolder(private val binding: ContentNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BeritaItem) {

            binding.title.text = data.title.toString()

        }

    }

}