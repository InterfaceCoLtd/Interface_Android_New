package com.example.interface_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val noticebutton = findViewById<Button>(R.id.button3)
        val menuimage = findViewById<ImageView>(R.id.imageView7)
        val voteimage = findViewById<Button>(R.id.button11)

        // MENU(ImageView)를 클릭했을 때의 동작 정의
        noticebutton.setOnClickListener {
            val intent = Intent(this@MainActivity, Notice::class.java)
            // Intent를 사용하여 다음 뷰로 이동
            startActivity(intent)
        }
        menuimage.setOnClickListener {
            // 다음 뷰로 이동하는 Intent 생성
            val intent = Intent(this@MainActivity, MenuActivity::class.java)
            // Intent를 사용하여 다음 뷰로 이동
            startActivity(intent)
        }
        voteimage.setOnClickListener {
            // 다음 뷰로 이동하는 Intent 생성
            val intent = Intent(this@MainActivity, vote::class.java)
            // Intent를 사용하여 다음 뷰로 이동
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        // 뒤로가기 버튼을 눌렀을 때 아무 동작도 하지 않도록 함
        // super.onBackPressed() // 만약 뒤로가기 동작을 유지하고 싶다면 이 주석을 해제할 수 있음
    }
}