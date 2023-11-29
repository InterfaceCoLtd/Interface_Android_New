package com.example.interface_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val homeimage = findViewById<ImageView>(R.id.imageView2)

        // MENU(ImageView)를 클릭했을 때의 동작 정의
        homeimage.setOnClickListener {
            val intent = Intent(this@MenuActivity, MainActivity::class.java)
            // Intent를 사용하여 다음 뷰로 이동
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
}