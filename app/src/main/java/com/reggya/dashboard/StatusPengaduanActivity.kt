package com.reggya.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast

class StatusPengaduanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_pengaduan)

        Toast.makeText(this, "Belum mengajukan pengaduan data", Toast.LENGTH_LONG).show()
    }


}