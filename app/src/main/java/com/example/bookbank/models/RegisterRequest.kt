package com.example.bookbank.models

data class RegisterRequest(
    val address: String,
    val cnic: String,
    val email: String,
    val father_name: String,
    val mobile: String,
    val name: String,
    val password: String
)