package com.reggya.dashboard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reggya.dashboard.databinding.ActivityCekBansosBinding

class CekBansosActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCekBansosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCekBansosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = arrayOf("Pilih Provinsi", "Pilih Kabupaten", "Pilih Kecamatan", "Pilih Desa")

        binding.provinsi.adapter = adapter(category[0])
        binding.kabupaten.adapter= adapter(category[1])
        binding.kecamatan.adapter = adapter(category[2])
        binding.desa.adapter = adapter(category[3])

        binding.cekBansos.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.BANSOS_URL))
            startActivity(intent)
        }

        Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show()

    }

    private fun adapter(category: String) : SpinnerAdapter =
        ArrayAdapter(this, com.reggya.dashboard.R.layout.text_view_padding, arrayOf(category))

}