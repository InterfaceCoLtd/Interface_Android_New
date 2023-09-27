package com.example.interface_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class activity_signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val button2: Button = findViewById(R.id.button2) //회원가입 버튼을 누르면 회원가입 완료 페이지로 이동한다.
        button2.setOnClickListener{
            val intent = Intent(this, activity_finish_signup::class.java)
            startActivity(intent)
        }
    }
}