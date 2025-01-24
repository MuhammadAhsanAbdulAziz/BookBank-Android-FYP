package com.example.bookbank.models

data class CurrentBorrowed(
    val book_title: String,
    val borrowed_status: String,
    val return_date: String
)