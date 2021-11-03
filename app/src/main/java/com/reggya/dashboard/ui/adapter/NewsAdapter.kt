package com.reggya.dashboard.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reggya.dashboard.data.ModelBeritaItem
import com.reggya.dashboard.databinding.ContentNewsBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = ArrayList<ModelBeritaItem?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<ModelBeritaItem?>?){
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
        fun bind(data: ModelBeritaItem?) {

            binding.title.text = data?.title.toString()
//            binding.content.text = data?.description
//            Glide.with(itemView)
//                .load(data?.urlToImage)
//                .into(binding.image)

//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, DetailNews::class.java)
//                intent.putExtra(DetailNews.DATA, data?.url)
//                itemView.context.startActivity(intent)
//            }
        }

    }

}