package com.example.bookbank.models

data class ReturnAndReturnBooksData(
    val address: String,
    val book_return_date: String,
    val books_details: List<BookData>,
    val books_required: List<BooksRequired>,
    val borrowed_status: String,
    val created_at: String,
    val father_name: String,
    val form_number: Int,
    val mobile: String,
    val name: String,
    val request_status: String,
    val student_cnic: String,
    val updated_at: String
)