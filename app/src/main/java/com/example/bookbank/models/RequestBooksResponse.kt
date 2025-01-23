package com.example.bookbank.models

data class RequestBooksResponse(
    val data: List<RequestBooksData>,
    val success: Boolean
)