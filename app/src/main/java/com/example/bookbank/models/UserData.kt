package com.example.bookbank.models

data class UserData(
    val address: String,
    val book_history: List<Any>,
    val cnic: String,
    val created_at: String,
    val current_borrowed: List<Any>,
    val email: String,
    val father_name: String,
    val mobile: String,
    val name: String,
    val password: String,
    val totalbooksborrowed: Int,
    val totalbooksreturned: Int,
    val totalnotreturnedbooks: Int,
    val updated_at: String
)