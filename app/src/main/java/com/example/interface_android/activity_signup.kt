package com.example.interface_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object ApiClient {
    private const val BASE_URL = "https://api.interfacesejong.xyz/api/swagger-ui/index.html#/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: SignUpService by lazy {
        retrofit.create(SignUpService::class.java)
    }
}
class activity_signup : AppCompatActivity() {

    lateinit var et_email:EditText
    lateinit var et_password:EditText
    lateinit var et_password2:EditText
    lateinit var button: Button
    lateinit var button2: Button

    private val apiService = ApiClient.apiService
    class activity_signup : AppCompatActivity() {

        lateinit var et_email: EditText
        lateinit var et_password: EditText
        lateinit var et_password2: EditText
        lateinit var button: Button
        lateinit var button2: Button
        lateinit var textview2: TextView

        // Retrofit2 서비스 인스턴스 초기화
        private val apiService = ApiClient.apiService

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_signup)
            textview2 = findViewById(R.id.textView2)
            et_email = findViewById(R.id.et_email)
            et_password = findViewById(R.id.et_passward)
            et_password2 = findViewById(R.id.et_passward2)
            button = findViewById(R.id.button)
            button2 = findViewById(R.id.button2) // 회원가입 버튼을 누르면 회원가입 완료 페이지로 이동한다.
            button2.setOnClickListener {
                val intent = Intent(this, activity_finish_signup::class.java)
                startActivity(intent)
            }

            // 중복 확인 버튼 클릭 이벤트 처리
            button.setOnClickListener {
                val emailToCheck = et_email.text.toString()

                // Retrofit2를 사용하여 서버에 이메일 중복 확인 요청 보내기
                val call = apiService.checkemailDuplicate(emailToCheck)
                call.enqueue(object : Callback<SignUp> {
                    override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                        if (response.isSuccessful) {
                            val signUpResponse = response.body()

                            // 여기에서 signUpResponse를 처리하여 중복 여부를 확인하고 메시지를 표시할 수 있습니다.
                            if (signUpResponse != null && signUpResponse.response == "NON DUPLICATION") {
                                // 이메일이 중복되지 않음
                                // 사용자에게 메시지를 표시하거나 다른 작업을 수행할 수 있습니다.
                                textview2.text = "사용가능한 이메일 입니다."
                            } else {
                                // 이메일이 중복되지 됨
                                textview2.text = "중복된 이메일입니다."
                            }
                        } else {
                            // 서버로부터 실패 응답을 받은 경우 처리
                        }
                    }

                    override fun onFailure(call: Call<SignUp>, t: Throwable) {
                        // 네트워크 오류 처리
                    }
                })
            }
        }
    }
}

interface SignUpService {
    @GET("/users/exists")
    fun checkemailDuplicate(@Query("response") response: String): Call<SignUp>
}