package com.example.bookbank.models

data class RequestBooksResponse(
    val data: List<ReturnAndReturnBooksData>,
    val success: Boolean
)