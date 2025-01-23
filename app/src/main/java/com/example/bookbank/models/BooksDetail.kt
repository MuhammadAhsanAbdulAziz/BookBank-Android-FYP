package com.example.bookbank.models

data class BooksDetail(
    val author: String,
    val availableCopies: Int,
    val category: String,
    val image: String,
    val price: Int,
    val title: String,
    val totalCopies: Int
)