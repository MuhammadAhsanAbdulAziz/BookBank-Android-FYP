package com.example.bookbank.models

data class Notification(
    val created_at: String,
    val email: String,
    val id: Int,
    val messages: Messages,
    val updated_at: String
)