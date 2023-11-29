package com.example.interface_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class vote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val mainbutton= findViewById<ImageView>(R.id.imageView14)
        val votevote= findViewById<ImageView>(R.id.imageView15)

        // MENU(ImageView)를 클릭했을 때의 동작 정의
        mainbutton.setOnClickListener {
            val intent = Intent(this@vote, MainActivity::class.java)
            // Intent를 사용하여 다음 뷰로 이동
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vote)
    }
}