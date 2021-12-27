package com.reggya.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reggya.dashboard.data.ApiResponseType
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.databinding.ActivityMainBinding
import com.reggya.dashboard.ui.HomeViewModel
import com.reggya.dashboard.ui.ViewModelFactory
import com.reggya.dashboard.ui.adapter.NewsAdapter
import android.R
import android.provider.Settings

import android.view.ViewGroup
import com.reggya.dashboard.utils.ConnectionLiveData


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkConnection()
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
            else if (it.type == ApiResponseType.ERROR){
                binding.serverError.visibility = View.VISIBLE
                binding.newtwork.visibility = View.INVISIBLE
            }

        })

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = newsAdapter
        }

        binding.dashboardMenu.menu1.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        binding.dashboardMenu.menu2.setOnClickListener {
            val intent = Intent(this, CekBansosActivity::class.java)
            startActivity(intent)
        }

        binding.dashboardMenu.menu3.setOnClickListener {
            val intent = Intent(this, PengaduanActivity::class.java)
            startActivity(intent)
        }

        binding.dashboardMenu.menu4.setOnClickListener {
            val intent = Intent(this, StatusPengaduanActivity::class.java)
            startActivity(intent)
        }

        binding.menu.setOnClickListener {
            Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.homeVector.setOnClickListener {
            val intent = Intent(this, PengaduanActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkConnection() {
        val cld = ConnectionLiveData(application)

        cld.observe(this, { isConnected ->
            if (isConnected){
                binding.newtwork.visibility = View.GONE
                binding.rvNews.visibility = View.VISIBLE
            }else{
                binding.rvNews.visibility = View.GONE
                binding.serverError.visibility = View.GONE
                binding.newtwork.visibility = View.VISIBLE
                binding.buttonNetwork.setOnClickListener {
                    cld.observe(this, { isConnected ->
                        if (isConnected){
                            binding.rvNews.visibility = View.VISIBLE
                            Toast.makeText(this, "Sip..koneksi internetmu sudah terhubung", Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this, "Yah..koneksi intermetmu masih terputus", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

        })
    }
}


