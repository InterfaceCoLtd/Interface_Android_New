package com.example.interface_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Activity_SplashView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_view)
        Handler().postDelayed(Runnable {
            var i = Intent(this@Activity_SplashView,LoginActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)
    }
}