package com.example.bookbank.models

data class BooksRequest(
    val address: String,
    val book_return_date: String,
    val books_required: List<BooksRequired>,
    val father_name: String,
    val mobile: String,
    val name: String,
    val student_cnic: String
)