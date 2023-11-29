package com.example.interface_android

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class activity_signup : AppCompatActivity() {

    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    private fun shakeTextView(textView: TextView) {
        val shakeAnimator = ObjectAnimator.ofFloat(textView, "translationX", 0f, 30f, -30f, 30f, -30f, 0f)
        shakeAnimator.duration = 1000 // 애니메이션의 전체 지속 시간 (밀리초)
        shakeAnimator.interpolator = AccelerateDecelerateInterpolator() // 애니메이션의 가속도/감속도 설정

        shakeAnimator.start()
    }
    // 예시로 만든 함수, 실제로는 여러 조건을 확인해야 함

    lateinit var et_email: EditText
    lateinit var et_password: EditText
    lateinit var et_password2: EditText
    lateinit var button: Button
    lateinit var button2: Button
    lateinit var textview2: TextView
    lateinit var textview3: TextView
    lateinit var textView: TextView

    // Retrofit2 서비스 인스턴스 초기화
    private val apiService = ApiClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        textview3 = findViewById(R.id.textView3) //비밀번호가 일치하는지 일치안하는지 뜨게 해줌
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_passward)
        et_password2 = findViewById(R.id.et_passward2)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        textView = findViewById(R.id.textView)

        button2.isEnabled = false
        et_password.isEnabled = false
        et_password2.isEnabled = false


        // 비밀번호가 일치하고 사용가능한 이메일일때 서버로부터 회원가입을 post하고 회원가입 완료 페이지로 넘어가기
        button2.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            val password2 = et_password2.text.toString()

            if (password == password2 && textView.text == "사용가능한 이메일이에요") {
                // 비밀번호가 일치하고, 이메일이 사용 가능한 경우
                val userData = User(email, password)



                // 서버에 POST 요청을 보내는 코드 추가
                val call = apiService.postSignUp(userData)
                call.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            // 서버 응답을 확인하고 적절한 처리를 수행
                            val signUpResponse = response.body()
                            if (signUpResponse != null) {
                                // 서버 응답을 기반으로 다음 화면으로 이동
                                val intent = Intent(this@activity_signup, activity_finish_signup::class.java)
                                startActivity(intent)
                            }
                        } else {
                            // 서버로부터 실패 응답을 받은 경우 처리
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        // 네트워크 오류 처리

                    }
                })
            }
        }

        // 중복 확인 버튼 클릭 이벤트 처리
        button.setOnClickListener {
            val emailToCheck = et_email.text.toString()
            if(isEmailValid(emailToCheck)) {
                // Retrofit2를 사용하여 서버에 이메일 중복 확인 요청 보내기
                val call = apiService.checkEmailDuplicate(emailToCheck)
                call.enqueue(object : Callback<EmailCheck> {
                    override fun onResponse(
                        call: Call<EmailCheck>,
                        response: Response<EmailCheck>
                    ) {
                        if (response.isSuccessful) {
                            val signUpResponse = response.body()

                            // 여기에서 signUpResponse를 처리하여 중복 여부를 확인하고 메시지를 표시할 수 있습니다.
                            if (signUpResponse != null) {
                                if (signUpResponse.response == "NON DUPLICATION") {
                                    // 이메일이 중복되지 않음
                                    textView.text = "사용가능한 이메일이에요"
                                    textView.setTextColor(
                                        ContextCompat.getColor(
                                            this@activity_signup,
                                            R.color.Green
                                        )
                                    )
                                    shakeTextView(textView)
                                    button.setBackgroundResource(R.drawable.button_gray_small)
                                    button.isEnabled = false
                                    et_email.isEnabled = false
                                    et_password.isEnabled = true
                                    et_password2.isEnabled = true
                                } else {
                                    // 이메일이 중복됨
                                    textView.text = "중복된 이메일이에요"
                                    textView.setTextColor(
                                        ContextCompat.getColor(
                                            this@activity_signup,
                                            R.color.Red
                                        )
                                    )
                                    shakeTextView(textView)
                                }
                            }
                        } else {
                            // 서버로부터 실패 응답을 받은 경우 처리
                        }
                    }


                    override fun onFailure(call: Call<EmailCheck>, t: Throwable) {
                        // 네트워크 오류 처리
                    }
                })
            }
            else{
                textView.text = "이메일을 형식을 입력해주세요"
                textView.setTextColor(
                    ContextCompat.getColor(
                        this@activity_signup,
                        R.color.Red
                    )
                )
                shakeTextView(textView)
            }
        }

        et_password.addTextChangedListener {
            val password = et_password.text.toString()
            val password2 = et_password2.text.toString()

            if (password.isNotEmpty() && password2.isNotEmpty()) {
                if (password == password2) {
                    // 비밀번호가 일치함
                    textview3.text = "비밀번호가 일치해요"
                    textview3.setTextColor(ContextCompat.getColor(this@activity_signup, R.color.Green))
                    shakeTextView(textview3)
                    button2.setBackgroundResource(R.drawable.button_light_big)
                    button2.isEnabled = true
                } else {
                    // 비밀번호가 일치하지 않음
                    textview3.text = "비밀번호가 일치하지 않아요"
                    textview3.setTextColor(ContextCompat.getColor(this@activity_signup, R.color.Red))
                    shakeTextView(textview3)
                    button2.setBackgroundResource(R.drawable.button_gray_big)
                    button2.isEnabled = false
                }
            } else {
                // 비밀번호가 하나라도 비어있음
                textview3.text = "비밀번호를 일치하지 않아요"
                textview3.setTextColor(ContextCompat.getColor(this@activity_signup, R.color.Red))
                shakeTextView(textview3)
                button2.setBackgroundResource(R.drawable.button_gray_big)
                button2.isEnabled = false
            }
        }

        et_password2.addTextChangedListener {
            val password = et_password.text.toString()
            val password2 = et_password2.text.toString()

            if (password.isNotEmpty() && password2.isNotEmpty()) {
                if (password == password2) {
                    // 비밀번호가 일치함
                    textview3.text = "비밀번호가 일치해요"
                    textview3.setTextColor(ContextCompat.getColor(this@activity_signup, R.color.Green))
                    shakeTextView(textview3)
                    button2.setBackgroundResource(R.drawable.button_light_big)
                    button2.isEnabled = true
                } else {
                    // 비밀번호가 일치하지 않음
                    textview3.text = "비밀번호가 일치하지 않아요"
                    textview3.setTextColor(ContextCompat.getColor(this@activity_signup, R.color.Red))
                    shakeTextView(textview3)
                    button2.setBackgroundResource(R.drawable.button_gray_big)
                    button2.isEnabled = false
                }
            } else {
                // 비밀번호가 하나라도 비어있음
                textview3.text = "비밀번호를 일치하지 않아요"
                textview3.setTextColor(ContextCompat.getColor(this@activity_signup, R.color.Red))
                shakeTextView(textview3)
                button2.setBackgroundResource(R.drawable.button_gray_big)
                button2.isEnabled = false
            }
        }
    }
}

