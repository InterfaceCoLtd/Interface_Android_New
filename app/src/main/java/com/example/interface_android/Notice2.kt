package com.example.interface_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Notice2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val backnotice= findViewById<ImageView>(R.id.imageView16)

        // MENU(ImageView)를 클릭했을 때의 동작 정의
        backnotice.setOnClickListener {
            val intent = Intent(this@Notice2, Notice::class.java)
            // Intent를 사용하여 다음 뷰로 이동
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice2)
    }
}