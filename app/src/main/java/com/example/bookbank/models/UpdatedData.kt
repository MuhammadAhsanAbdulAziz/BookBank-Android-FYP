package com.example.bookbank.models

data class UpdatedData(
    val address: String,
    val book_history: List<Any>,
    val created_at: String,
    val current_borrowed: List<Any>,
    val email: String,
    val father_name: String,
    val mobile: String,
    val name: String,
    val password: String,
    val student_cnic: String,
    val totalBooksBorrowed: Int,
    val totalBooksNotReturned: Int,
    val totalBooksReturned: Int,
    val updated_at: String
)