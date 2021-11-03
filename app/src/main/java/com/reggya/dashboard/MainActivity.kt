package com.reggya.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Xml
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reggya.dashboard.data.ApiResponseType
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.data.XMLHandler
import com.reggya.dashboard.databinding.ActivityMainBinding
import com.reggya.dashboard.ui.HomeViewModel
import com.reggya.dashboard.ui.ViewModelFactory
import com.reggya.dashboard.ui.adapter.NewsAdapter
import org.json.JSONException
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    private var list:  List<BeritaItem>? = null

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
//                val parser = XMLHandler()
//                val instream = assets.open("response.xml")
//                parser.parseXML(instream)
//                val jsonObject = JSONException
//                newsAdapter.setData(it.data)
                val p = it.data?.get(0)?.title
                print(p)
//                newsAdapter.notifyDataSetChanged()
            }
        })

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }

        binding.dashboardMenu.menu1.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}


