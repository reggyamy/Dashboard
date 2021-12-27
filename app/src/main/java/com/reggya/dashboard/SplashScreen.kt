package com.reggya.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat.postDelayed


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Looper.myLooper()?.let {
            Handler(it).postDelayed(Runnable {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2500)
        }
    }
}