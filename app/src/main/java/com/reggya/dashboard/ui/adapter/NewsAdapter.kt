package com.reggya.dashboard.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reggya.dashboard.BuildConfig
import com.reggya.dashboard.DetailNews
import com.reggya.dashboard.R
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.data.HTMLHelper
import com.reggya.dashboard.databinding.ContentNewsBinding


class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = ArrayList<BeritaItem?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<BeritaItem?>?){
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
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int = news.size

    class ViewHolder(private val binding: ContentNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BeritaItem?) {

            binding.title.text = data?.title

            Glide.with(itemView)
                .load(HTMLHelper.getString(data?.isi)[0])
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(binding.image)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailNews::class.java)
                intent.putExtra(DetailNews.DATA, data)
                itemView.context.startActivity(intent)
            }

        }

    }

}



