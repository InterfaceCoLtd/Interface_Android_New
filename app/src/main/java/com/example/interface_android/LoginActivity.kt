package com.example.interface_android

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private fun shakeTextView(textView: TextView) {
        val shakeAnimator = ObjectAnimator.ofFloat(textView, "translationX", 0f, 30f, -30f, 30f, -30f, 0f)
        shakeAnimator.duration = 500 // 애니메이션의 전체 지속 시간 (밀리초)
        shakeAnimator.interpolator = AccelerateDecelerateInterpolator() // 애니메이션의 가속도/감속도 설정

        shakeAnimator.start()
    }

    lateinit var et_id: EditText
    lateinit var et_pw: EditText
    lateinit var bt_login: Button
    lateinit var tv_alert: TextView

    private val apiService = ApiClient.apiService
    override fun onCreate(savedInstanceState: Bundle?) { //Activity 가 실행되면 처음 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_id = findViewById(R.id.et_id)
        et_pw = findViewById(R.id.et_pw)
        tv_alert = findViewById(R.id.tv_alert)
        bt_login = findViewById(R.id.bt_login)

        bt_login.setOnClickListener {
            val id = et_id.text.toString()
            val pw = et_pw.text.toString()

            val user = User(id, pw)

            val call = apiService.postLogin(user)
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        // 서버 응답을 확인하고 적절한 처리를 수행
                        val signUpResponse = response.body()
                        if (signUpResponse != null) {
                            // 서버 응답을 기반으로 다음 화면으로 이동
                            val intent1 = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent1)
                        }
                    } else {
                        // 서버로부터 실패 응답을 받은 경우 처리
                        tv_alert.text = "아이디 또는 비밀번호가 잘못되었어요"
                        tv_alert.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.Red))
                        et_id.setBackgroundResource(R.drawable.edit_text_fail)
                        et_pw.setBackgroundResource(R.drawable.edit_text_fail)
                        shakeTextView(et_id)
                        shakeTextView(et_pw)
                        shakeTextView(tv_alert)
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    // 네트워크 오류 처리

                }
            })
        }

        val bt_signup: TextView = findViewById(R.id.bt_signup) //회원가입 버튼을 누르면 회원가입 페이지로 이동한다.
        bt_signup.setOnClickListener{
            val intent2 = Intent(this, activity_signup::class.java)
            startActivity(intent2)
        }
    }

}