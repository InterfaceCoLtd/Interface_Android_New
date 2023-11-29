package com.example.interface_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Notice : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        val mainImage = findViewById<ImageView>(R.id.imageView11)

        mainImage.setOnClickListener {
            val intent = Intent(this@Notice, MainActivity::class.java)
            // Intent를 사용하여 다음 뷰로 이동
            startActivity(intent)
        }

        val gonotice = findViewById<Button>(R.id.button9)

        gonotice.setOnClickListener {
            val intent = Intent(this@Notice, Notice2::class.java)
            // Intent를 사용하여 다음 뷰로 이동
            startActivity(intent)
        }

    }
}