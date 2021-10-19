//package com.reggya.dashboard.ui.adapter
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.reggya.dashboard.CovidResponseItem
//import com.reggya.dashboard.databinding.ContentBinding
//import com.reggya.dashboard.databinding.ContentNewsBinding
//
//class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
//
//    private var data = ArrayList<CovidResponseItem>()
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(newData: List<CovidResponseItem>?){
//        if (newData == null) return
//        data.clear()
//        data.addAll(newData)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return  ViewHolder(ContentNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val _data = data[position]
//        holder.bind(_data)
//    }
//
//    override fun getItemCount(): Int = data.size
//
//    class ViewHolder(private val binding: ContentNewsBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(_data: CovidResponseItem) {
////            binding.sembuh.text = _data.attributes.kasusSembub.toString()
//        }
//
//    }
//}