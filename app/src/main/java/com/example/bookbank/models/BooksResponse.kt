package com.example.bookbank.models

data class BooksResponse(
    val data: List<BookData>,
    val success: Boolean
)