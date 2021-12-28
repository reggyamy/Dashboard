package com.reggya.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StatusPengaduanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_pengaduan)

        Toast.makeText(this, "Belum mengajukan pengaduan data", Toast.LENGTH_LONG).show()
    }


}