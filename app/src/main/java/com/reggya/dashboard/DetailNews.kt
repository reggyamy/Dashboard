package com.reggya.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.databinding.ActivityDetailNewsBinding

class DetailNews : AppCompatActivity() {

    companion object{
        const val DATA = ""
    }

    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra(DATA)

        binding.webViewNews.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(data.toString())
                return true
            }
        }
        binding.webViewNews.loadUrl(data.toString())
    }
}