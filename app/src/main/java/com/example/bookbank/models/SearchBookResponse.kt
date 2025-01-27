package com.example.bookbank.models

data class SearchBookResponse(
    val books: List<BookData>,
    val success: Boolean
)