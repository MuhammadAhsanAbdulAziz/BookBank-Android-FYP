package com.example.bookbank.models

data class UpdateStudentResponse(
    val message: String,
    val success: Boolean,
    val updatedData: UserData
)