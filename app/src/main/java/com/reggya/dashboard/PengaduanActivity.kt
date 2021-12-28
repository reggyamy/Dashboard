package com.reggya.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reggya.dashboard.databinding.ActivityPengaduanBinding


class PengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengaduanBinding
    var category = arrayOf("Sanggah","Usulan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, com.reggya.dashboard.R.layout.text_view_padding, category
        )

        spinnerArrayAdapter.setDropDownViewResource(com.reggya.dashboard.R.layout.text_view_padding)

        binding.spinner.adapter = spinnerArrayAdapter
        binding.spinner.prompt = category[0]
        binding.spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                (parent.getChildAt(0) as TextView).setTextSize(12f)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })

        Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show()

    }
}