package com.reggya.dashboard

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.reggya.dashboard.data.BeritaItem
import com.reggya.dashboard.data.HTMLHelper
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

        val data = intent.getParcelableExtra<BeritaItem>(DATA)

        Glide.with(this)
            .load(HTMLHelper.getString(data?.isi)[0])
            .placeholder(R.drawable.ic_no_image_detail)
            .error(R.drawable.ic_no_image_detail)
            .into(binding.detail.image)

        binding.detail.apply {
            title.text = data?.title
            var isi = ""
            if (data?.isi?.contains("<img") == true){
                for (i in data.isi?.indexOf("<img")!!..data.isi!!.indexOf("/></p>")+6){
                    isi = data.isi!!.drop(i)

                }
            }else{
                isi = data?.isi.toString()
            }

            content.text = Html.fromHtml(isi)

            back.setOnClickListener {
                startActivity(Intent(this@DetailNews, MainActivity::class.java))
            }
        }


    }
}