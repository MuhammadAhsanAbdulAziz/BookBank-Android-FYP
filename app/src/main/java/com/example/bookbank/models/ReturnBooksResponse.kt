package com.example.bookbank.models

data class ReturnBooksResponse(
    val data: List<ReturnAndReturnBooksData>,
    val success: Boolean
)