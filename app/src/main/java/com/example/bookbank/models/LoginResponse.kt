package com.example.bookbank.models

data class LoginResponse(
    val data: UserData,
    val jwt: String,
    val success: Boolean
)