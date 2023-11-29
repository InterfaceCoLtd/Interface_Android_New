package com.example.interface_android

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


object ApiClient {
    private const val BASE_URL = "https://api.interfacesejong.xyz/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("/api/users/exists")
    fun checkEmailDuplicate(@Query("email") email: String): Call<EmailCheck>

    @POST("/api/users")
    fun postSignUp(
        @Body signupFinish: User
    ): Call<User>

    @POST("/api/users/auth/sign-in")
    fun postLogin(
        @Body login: User
    ): Call<User>
}
