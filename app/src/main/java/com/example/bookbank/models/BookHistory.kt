package com.example.bookbank.models

data class BookHistory(
    val book_title: String,
    val borrowed_status: String,
    val return_date: String
)