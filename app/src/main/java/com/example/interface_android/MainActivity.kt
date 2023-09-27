package com.example.interface_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //Activity 가 실행되면 처음 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt_signup: Button = findViewById(R.id.bt_signup) //회원가입 버튼을 누르면 회원가입 페이지로 이동한다.
        bt_signup.setOnClickListener{
            val intent = Intent(this, activity_signup::class.java)
            startActivity(intent)
        }
    }

}