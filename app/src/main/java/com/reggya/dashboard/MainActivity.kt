package com.reggya.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.reggya.dashboard.data.ApiResponseType
import com.reggya.dashboard.databinding.ActivityMainBinding
import com.reggya.dashboard.ui.HomeViewModel
import com.reggya.dashboard.ui.ViewModelFactory
import com.reggya.dashboard.ui.adapter.NewsAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModel()
        setUpUI()

    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory
                    .getInstance(this))[HomeViewModel::class.java]

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpUI() {
        val newsAdapter = NewsAdapter()
        viewModel.getNews.observe(this, {
            if (it.type == ApiResponseType.SUCCESS && it != null){
                newsAdapter.setData(it.data)
                newsAdapter.notifyDataSetChanged()
            }
        })

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }


}


