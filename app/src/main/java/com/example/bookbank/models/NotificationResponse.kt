package com.example.bookbank.models

data class NotificationResponse(
    val notifications: List<Notification>,
    val success: Boolean
)