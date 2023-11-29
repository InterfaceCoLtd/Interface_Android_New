package com.example.interface_android


data class User(
    val email: String,
    val password : String,
    val deviceId: String = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
)


data class EmailCheck(
    val response: String
)

