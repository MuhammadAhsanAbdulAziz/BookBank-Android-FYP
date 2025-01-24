package com.example.bookbank.api

import com.example.bookbank.models.NotificationResponse
import com.example.bookbank.models.UpdateStudentRequest
import com.example.bookbank.models.UpdateStudentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface StudentApi {

    @PUT("database/students/update")
    suspend fun updateStudent(@Body updateStudentRequest: UpdateStudentRequest): Response<UpdateStudentResponse>

    @GET("database/notification/select")
    suspend fun getNotifications(): Response<NotificationResponse>

}